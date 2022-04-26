package com.wobReporting.client.service;

import com.wobReporting.server.service.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Profile("!test")
@Component
public class Reporter implements CommandLineRunner {
    @Autowired
    ReporterService reporterService;

    @Override
    public void run(String... args) {
        reporterService.reportAll();
    }


}
