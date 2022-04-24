package com.wobReporting.server.service;

import com.wobReporting.client.rest.ListingRestClient;
import com.wobReporting.client.rest.ListingStatusRestClient;
import com.wobReporting.client.rest.LocationRestClient;
import com.wobReporting.client.rest.MarketplaceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SynchronizerService {

    @Autowired
    private MarketplaceService marketplaceService;
    @Autowired
    private ListingStatusService listingStatusService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ListingService listingService;
    @Autowired
    private ListingRestClient listingRestClient;
    @Autowired
    private ListingStatusRestClient listingStatusRestClient;
    @Autowired
    private LocationRestClient locationRestClient;
    @Autowired
    private MarketplaceRestClient marketplaceRestClient;

    public SynchronizerService() {
    }

    public void synchronizeAll() {
        synchronizeMarketplaces();
        synchronizeListingStatuses();
        synchronizeLocations();
        synchronizeListings();
    }

    private void synchronizeMarketplaces() {
        marketplaceService.saveAll(marketplaceRestClient.getJson());
    }

    private void synchronizeListingStatuses() {
        listingStatusService.saveAll(listingStatusRestClient.getJson());
    }

    private void synchronizeLocations() {
        locationService.saveAll(locationRestClient.getJson());
    }

    private void synchronizeListings() {
        listingService.saveAll(listingRestClient.getJson());
    }
}
