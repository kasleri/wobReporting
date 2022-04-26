package com.wobReporting.server.repository.helper.reporter.Data;

import com.wobReporting.server.repository.helper.reporter.AbstractIMarketplaceReports;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReporterDTO {
    private int totalCount;
    private String bestLister;
    private AbstractIMarketplaceReports totalEbayStatistics;
    private AbstractIMarketplaceReports totalAmazonStatistics;
    private List<? extends AbstractIMarketplaceReports> monthlyEbayStatistics;
    private List<? extends AbstractIMarketplaceReports> monthlyAmazonStatistics;
    private List<BestLister> monthlyBestLister;

    public ReporterDTO() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getBestLister() {
        return bestLister;
    }

    public void setBestLister(String bestLister) {
        this.bestLister = bestLister;
    }

    public AbstractIMarketplaceReports getTotalEbayStatistics() {
        return totalEbayStatistics;
    }

    public void setTotalEbayStatistics(AbstractIMarketplaceReports totalEbayStatistics) {
        this.totalEbayStatistics = totalEbayStatistics;
    }

    public AbstractIMarketplaceReports getTotalAmazonStatistics() {
        return totalAmazonStatistics;
    }

    public void setTotalAmazonStatistics(AbstractIMarketplaceReports totalAmazonStatistics) {
        this.totalAmazonStatistics = totalAmazonStatistics;
    }

    public List<? extends AbstractIMarketplaceReports> getMonthlyEbayStatistics() {
        return monthlyEbayStatistics;
    }

    public void setMonthlyEbayStatistics(List<? extends AbstractIMarketplaceReports> monthlyEbayStatistics) {
        this.monthlyEbayStatistics = monthlyEbayStatistics;
    }

    public List<? extends AbstractIMarketplaceReports> getMonthlyAmazonStatistics() {
        return monthlyAmazonStatistics;
    }

    public void setMonthlyAmazonStatistics(List<? extends AbstractIMarketplaceReports> monthlyAmazonStatistics) {
        this.monthlyAmazonStatistics = monthlyAmazonStatistics;
    }

    public List<BestLister> getMonthlyBestLister() {
        return monthlyBestLister;
    }

    public void setMonthlyBestLister(List<BestLister> monthlyBestLister) {
        this.monthlyBestLister = monthlyBestLister;
    }
}
