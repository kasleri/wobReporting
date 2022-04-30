package com.wobReporting.client.rest;


import com.wobReporting.server.model.Listing;
import com.wobReporting.server.model.ListingStatus;
import com.wobReporting.server.model.Location;
import com.wobReporting.server.model.Marketplace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class RestClientTest {
    @Autowired
    private ListingRestClient listingRestClient;
    @Autowired
    private MarketplaceRestClient marketplaceRestClient;
    @Autowired
    private ListingStatusRestClient listingStatusRestClient;
    @Autowired
    private LocationRestClient locationRestClient;

    @Test
    public void responseCreatedTest() {

        List<Listing> response = listingRestClient.getJson();

        assertThat(response.isEmpty()).isFalse();
    }

    @Test
    public void deserializerListingTest() {
        List<Listing> result = listingRestClient.getJson();
        System.out.println(result);
    }

    @Test
    public void deserializerMarketplaceTest() {
        List<Marketplace> result = marketplaceRestClient.getJson();
        System.out.println(result);
    }

    @Test
    public void deserializerListingStatusTest() {
        List<ListingStatus> result = listingStatusRestClient.getJson();
        System.out.println(result);
    }

    @Test
    public void deserializerLocationTest() {
        List<Location> result = locationRestClient.getJson();
        System.out.println(result);
    }
}