package com.wobReporting.server.service;

import com.wobReporting.server.CSV.WriteCSVLog;
import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.ListingRepository;
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
            WriteCSVLog logCSV = new WriteCSVLog(errorLines);
            logCSV.printCSV();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
