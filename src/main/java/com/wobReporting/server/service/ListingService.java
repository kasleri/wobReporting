package com.wobReporting.server.service;

import com.wobReporting.server.csv.WriteCSVLog;
import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.ListingRepository;
import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.IBestLister;
import com.wobReporting.server.repository.helper.reporter.Data.MonthlyIBaseStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private MarketplaceService marketplaceService;
    @Autowired
    private Validator validator;
    @Autowired
    private WriteCSVLog csvWriter;

    public void saveAll(List<Listing> listings) {
        List<String[]> errorLines = new ArrayList<>();
        for (Listing listing : listings) {
            Set<ConstraintViolation<Listing>> violations = validator.validate(listing);
            if (violations.isEmpty()) {
                listingRepository.save(listing);
            } else {
                violations.forEach(v ->
                        errorLines.add(new String[]
                                {String.valueOf(listing.getId()),
                                        String.valueOf(marketplaceService.findById(listing.getMarketplace().longValue()).get().getMarketplace_name()),
                                        String.valueOf(v.getPropertyPath())
                                }));
            }
        }

        if (!errorLines.isEmpty()) {
            writeErrorsToCSV(errorLines);
        }
    }

    private void writeErrorsToCSV(List<String[]> errorLines) {
        try {
            csvWriter.setErrorLines(errorLines);
            csvWriter.printCSV();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getTotalBestLister() {
        return listingRepository.getTotalBestLister();
    }

    public List<MonthlyIBaseStatistics> getMonthlyStatistics() {
        return listingRepository.getMonthlyStatistics();
    }

    public List<IBaseStatistics> getTotalStatistics() {
        return listingRepository.getTotalStatistics();
    }

    public List<Listing> findAll() {
        return listingRepository.findAll();
    }

    public List<IBestLister> getMonthlyBestLister() {
        return listingRepository.getMonthlyBestLister();
    }
}
