package com.wobReporting.server.repository.helper.reporter;

import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class MarketplaceReports extends AbstractIMarketplaceReports {
    public MarketplaceReports() {
    }

    public MarketplaceReports(List<? extends IBaseStatistics> results) {
        super(results);
    }

    public MarketplaceReports(List<? extends IBaseStatistics> groupedFilteredResults, LocalDate month) {
        super(groupedFilteredResults);
        this.month = YearMonth.from(month).toString();
    }
}
