package com.wobReporting.server.csv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WriteCSVLog {

    @Value("${log.import-log.file-name}")
    private String importLogFileName;

    private List<String[]> errorLines;

    public WriteCSVLog() {
    }


    public void setErrorLines(List<String[]> errorLines) {
        this.errorLines = errorLines;
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void printCSV() throws IOException {
        File csvOutputFile = new File(importLogFileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            errorLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
