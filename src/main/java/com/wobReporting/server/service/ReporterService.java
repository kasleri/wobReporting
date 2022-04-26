package com.wobReporting.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wobReporting.config.PropertiesLoader;
import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.helper.reporter.AbstractIMarketplaceReports;
import com.wobReporting.server.repository.helper.reporter.Data.BestLister;
import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.MonthlyIBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.ReporterDTO;
import com.wobReporting.server.repository.helper.reporter.MonthlyMarketplaceReports;
import com.wobReporting.server.repository.helper.reporter.TotalMarketplaceReports;
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
        List<IBaseStatistics> allGroupedResult = listingService.getTotalStatistics();
        reporterDTO.setTotalEbayStatistics(generateTotalMarketplaceStatistics(allGroupedResult, "EBAY"));
        reporterDTO.setTotalAmazonStatistics(generateTotalMarketplaceStatistics(allGroupedResult, "AMAZON"));
    }

    private AbstractIMarketplaceReports generateTotalMarketplaceStatistics(List<IBaseStatistics> allGroupedResult, String marketplaceName) {
        return new TotalMarketplaceReports(
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
        reporterDTO.setMonthlyAmazonStatistics(generateMonthlyMarketplaceStatistics(monthlyGroupedResult, "AMAZON"));
        reporterDTO.setMonthlyEbayStatistics(generateMonthlyMarketplaceStatistics(monthlyGroupedResult, "EBAY"));
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
                .map(x -> new MonthlyMarketplaceReports(x.getValue(), x.getKey()))
                .collect(Collectors.toList());

    }

    private void generateMonthlyBestLister() {
        reporterDTO.setMonthlyBestLister(
                listingService.getMonthlyBestLister().stream()
                        .map(x -> new BestLister(x.getMonth(), x.getBestLister()))
                        .collect(Collectors.toList()));
    }

    private void writeToJsonFile(ReporterDTO objectToWrite) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(reportFile), objectToWrite);
    }
}
