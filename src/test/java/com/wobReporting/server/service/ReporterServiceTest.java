package com.wobReporting.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wobReporting.config.PropertiesLoader;
import com.wobReporting.server.repository.helper.reporter.Data.ReporterDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
class ReporterServiceTest {
    @Autowired
    private ReporterService reporterService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
    }

    @Test
    void reportAllTest() throws IOException {
        reporterService.reportAll();
        ReporterDTO expectedRepostDTO = reporterService.getReporterDTO();
        ReporterDTO reportDto = objectMapper.readValue(new File(PropertiesLoader.getProperty("report.json.file-name")), ReporterDTO.class);
        assertEquals(reportDto.getClass(), ReporterDTO.class);
        assertThat(reportDto)
                .isEqualToComparingFieldByFieldRecursively(expectedRepostDTO);
        assertEquals(expectedRepostDTO.getBestLister(), reportDto.getBestLister());
    }

    @Test
    void generateTotalStatisticTest() throws IOException {

        reporterService.generateTotalStatistics();
        ReporterDTO expectedRepostDTO = reporterService.getReporterDTO();
        reporterService.writeToJsonFile(expectedRepostDTO);
        ReporterDTO reportDto = objectMapper.readValue(new File(PropertiesLoader.getProperty("report.json.file-name")), ReporterDTO.class);
        assertEquals(reportDto.getClass(), ReporterDTO.class);
        assertThat(reportDto)
                .isEqualToComparingFieldByFieldRecursively(expectedRepostDTO);
        //assertEquals(expectedRepostDTO.getTotalAmazonStatistics().getTotalListingCount(), reportDto.getTotalAmazonStatistics().getTotalListingCount());
        //ssertEquals(expectedRepostDTO.getTotalEbayStatistics().getTotalListingCount(), reportDto.getTotalEbayStatistics().getTotalListingCount());
    }

    @Test
    void generateMonthlyStatisticTest() throws IOException {
        reporterService.generateMonthlyStatistics();
        ReporterDTO expectedRepostDTO = reporterService.getReporterDTO();
        reporterService.writeToJsonFile(expectedRepostDTO);
        ReporterDTO reportDto = objectMapper.readValue(new File(PropertiesLoader.getProperty("report.json.file-name")), ReporterDTO.class);
        assertEquals(reportDto.getClass(), ReporterDTO.class);
        assertThat(reportDto)
                .isEqualToComparingFieldByFieldRecursively(expectedRepostDTO);
        //assertEquals(expectedRepostDTO.getMonthlyAmazonStatistics(), reportDto.getTotalAmazonStatistics().getTotalListingCount());
        //assertEquals(expectedRepostDTO.getTotalEbayStatistics().getTotalListingCount(), reportDto.getTotalEbayStatistics().getTotalListingCount());
    }
}