package com.wobReporting.client.service;

import com.wobReporting.client.rest.ListingRestClient;
import com.wobReporting.client.rest.ListingStatusRestClient;
import com.wobReporting.client.rest.LocationRestClient;
import com.wobReporting.client.rest.MarketplaceRestClient;
import com.wobReporting.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Profile("!test")
@Component
public class Synchronizer implements CommandLineRunner {
    @Autowired
    SynchronizerService synchronizer;
    @Override
    public void run(String... args) {
//        synchronizer.synchronizeAll();
    }
}
