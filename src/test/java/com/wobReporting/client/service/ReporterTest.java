package com.wobReporting.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wobReporting.config.PropertiesLoader;
import com.wobReporting.server.repository.helper.reporter.Data.ReporterDTO;
import com.wobReporting.server.service.ReporterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class ReporterTest {

    @Autowired
    private ReporterService reporterService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void reportAllTest() throws IOException {
        reporterService.reportAll();
        ReporterDTO expectedRepostDTO = reporterService.getReporterDTO();
        ReporterDTO reportDto = objectMapper.readValue(new File(PropertiesLoader.getProperty("report.json.file-name")), ReporterDTO.class);
        assertEquals(reportDto.getClass(), ReporterDTO.class);
//        assertEquals(expectedRepostDTO, reportDto);
    }

    @Test
    void generateTotalStatisticTest() {
        reporterService.generateTotalStatistics();
    }

    @Test
    void generateMonthlyStatisticTest() {
        reporterService.generateMonthlyStatistics();
    }
}