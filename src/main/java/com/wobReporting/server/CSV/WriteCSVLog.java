package com.wobReporting.server.CSV;

import com.wobReporting.config.PropertiesLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteCSVLog {

    private final List<String[]> errorLines;

    public WriteCSVLog(List<String[]> errorLines) {
        this.errorLines = errorLines;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void printCSV() throws IOException {
        File csvOutputFile = new File(PropertiesLoader.getProperty("log.import-log.file-name"));
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
