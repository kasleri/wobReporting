package com.wobReporting.server.repository.helper.reporter.Data;

import java.time.LocalDate;
import java.time.YearMonth;

public class BestLister {
    private String month;
    private String bestLister;

    public BestLister() {
    }

    public BestLister(LocalDate month, String bestLister) {
        this.month = YearMonth.from(month).toString();
        this.bestLister = bestLister;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBestLister() {
        return bestLister;
    }

    public void setBestLister(String bestLister) {
        this.bestLister = bestLister;
    }
}
