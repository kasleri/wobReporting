package com.wobReporting.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wobReporting.client.ftp.FtpClient;
import com.wobReporting.config.PropertiesLoader;
import com.wobReporting.server.model.Listing;
import com.wobReporting.server.model.Marketplace;
import com.wobReporting.server.repository.helper.reporter.AbstractIMarketplaceReports;
import com.wobReporting.server.repository.helper.reporter.Data.BestLister;
import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.MonthlyIBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.ReporterDTO;
import com.wobReporting.server.repository.helper.reporter.MarketplaceReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ReporterService {
    @Autowired
    private ListingService listingService;
    @Autowired
    private MarketplaceService marketplaceService;
    @Autowired
    private ReporterDTO reporterDTO;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final File reportFile = new File(PropertiesLoader.getProperty("report.json.file-name"));
    private List<Marketplace> marketplaces;

    public ReporterService() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM"));
    }

    public ReporterDTO getReporterDTO() {
        return reporterDTO;
    }

    public void reportAll() {
        try {

            generateTotalStatistics();
            generateMonthlyStatistics();

            writeToJsonFile(reporterDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTotalStatistics() {
        generateTotalListingCount();
        generateTotalBestLister();
        marketplaces = marketplaceService.findAll();
        List<IBaseStatistics> allGroupedResult = listingService.getTotalStatistics();
        reporterDTO.setTotalMarketplaceStatistics(marketplaces.stream()
                .map(x -> generateTotalMarketplaceStatistics(allGroupedResult, x.getMarketplace_name()))
                .collect(Collectors.toList())
        );
    }

    private MarketplaceReports generateTotalMarketplaceStatistics(List<IBaseStatistics> allGroupedResult, String marketplaceName) {
        return new MarketplaceReports(
                allGroupedResult.stream()
                        .filter(x -> x.getMarketplaceName().equals(marketplaceName))
                        .collect(Collectors.toList()));
    }

    public void generateTotalListingCount() {
        List<Listing> allEntity = listingService.findAll();
        reporterDTO.setTotalCount(allEntity.stream().mapToInt(Listing::getQuantity).sum());
    }

    private void generateTotalBestLister() {
        reporterDTO.setBestLister(listingService.getTotalBestLister());
    }

    public void generateMonthlyStatistics() {

        generateMonthlyBestLister();
        List<MonthlyIBaseStatistics> monthlyGroupedResult = listingService.getMonthlyStatistics();
        marketplaces = marketplaceService.findAll();
        marketplaces.forEach(x -> reporterDTO.setMonthlyMarketStatistics(generateMonthlyMarketplaceStatistics(monthlyGroupedResult, x.getMarketplace_name())));
    }

    private List<? extends AbstractIMarketplaceReports> generateMonthlyMarketplaceStatistics(List<MonthlyIBaseStatistics> monthlyGroupedResult, String marketplaceName) {
        List<MonthlyIBaseStatistics> amazonMonthlyGroupedResults = monthlyGroupedResult.stream()
                .filter(x -> x.getMarketplaceName().equals(marketplaceName))
                .collect(Collectors.toList());

        Map<LocalDate, List<MonthlyIBaseStatistics>> statsPerMonth = amazonMonthlyGroupedResults.stream()
                .collect(groupingBy(MonthlyIBaseStatistics::getActMonth));

        Map<LocalDate, List<MonthlyIBaseStatistics>> sortedStatsPerMonth = statsPerMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return sortedStatsPerMonth.entrySet().stream()
                .map(x -> new MarketplaceReports(x.getValue(), x.getKey()))
                .collect(Collectors.toList());

    }

    private void generateMonthlyBestLister() {
        reporterDTO.setMonthlyBestLister(
                listingService.getMonthlyBestLister().stream()
                        .map(x -> new BestLister(x.getMonth(), x.getBestLister()))
                        .collect(Collectors.toList()));
    }

    public void writeToJsonFile(ReporterDTO objectToWrite) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(reportFile), objectToWrite);
    }

    public void uploadReportFileToFtp() throws IOException {
        FtpClient ftpClient = new FtpClient(PropertiesLoader.getProperty("ftp.client.server"),
                Integer.parseInt(PropertiesLoader.getProperty("ftp.client.port")),
                PropertiesLoader.getProperty("ftp.client.user"),
                PropertiesLoader.getProperty("ftp.client.password"));
        ftpClient.open();
        File file = new File(PropertiesLoader.getProperty("report.json.file-name"));

        ftpClient.putFileToPath(file, "report.json");
    }
}
