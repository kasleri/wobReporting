package com.wobReporting.server.service;

import com.wobReporting.server.model.ListingStatus;
import com.wobReporting.server.repository.ListingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingStatusService {
    @Autowired
    private ListingStatusRepository listingStatusRepository;

    public void saveAll(List<ListingStatus> listingStatuses) {
        listingStatusRepository.saveAll(listingStatuses);
    }
}
