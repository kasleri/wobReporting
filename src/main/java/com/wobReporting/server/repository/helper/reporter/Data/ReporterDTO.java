package com.wobReporting.server.repository.helper.reporter.Data;

import com.wobReporting.server.repository.helper.reporter.AbstractIMarketplaceReports;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReporterDTO {
    private int totalCount;
    private String bestLister;
    //private AbstractIMarketplaceReports totalEbayStatistics;
    private List<? extends AbstractIMarketplaceReports> totalMarketplaceStatistics;
    private List<? extends AbstractIMarketplaceReports> monthlyMarketStatistics;
    //private List<? extends AbstractIMarketplaceReports> monthlyAmazonStatistics;
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

    public List<? extends AbstractIMarketplaceReports> getTotalMarketplaceStatistics() {
        return totalMarketplaceStatistics;
    }

    public void setTotalMarketplaceStatistics(List<? extends AbstractIMarketplaceReports> totalMarketplaceStatistics) {
        this.totalMarketplaceStatistics = totalMarketplaceStatistics;
    }

    public List<? extends AbstractIMarketplaceReports> getMonthlyMarketStatistics() {
        return monthlyMarketStatistics;
    }

    public void setMonthlyMarketStatistics(List<? extends AbstractIMarketplaceReports> monthlyMarketStatistics) {
        this.monthlyMarketStatistics = monthlyMarketStatistics;
    }

    public List<BestLister> getMonthlyBestLister() {
        return monthlyBestLister;
    }

    public void setMonthlyBestLister(List<BestLister> monthlyBestLister) {
        this.monthlyBestLister = monthlyBestLister;
    }
}
