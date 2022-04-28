package com.wobReporting.client.service;

import com.wobReporting.server.service.ReporterService;
import com.wobReporting.server.service.SynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Profile("!test")
@Component
public class BaseRunner implements CommandLineRunner {
    @Autowired
    SynchronizerService synchronizer;
    @Autowired
    ReporterService reporterService;

    @Override
    public void run(String... args) throws IOException {
        synchronizer.synchronizeAll();
        reporterService.reportAll();
        reporterService.uploadReportFileToFtp();
    }


}
