package com.wobReporting.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wobReporting.config.PropertiesLoader;
import com.wobReporting.server.repository.helper.reporter.Data.ReporterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
class ReporterServiceTest {
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