package com.wobReporting.client.rest;


import com.wobReporting.server.model.Listing;
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

    @Test
    public void responseCreatedTest() {

        List<Listing> response = listingRestClient.getJson();

        assertThat(response.isEmpty()).isFalse();
    }
}