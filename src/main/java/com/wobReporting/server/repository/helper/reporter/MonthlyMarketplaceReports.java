package com.wobReporting.server.repository.helper.reporter;

import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class MonthlyMarketplaceReports extends AbstractIMarketplaceReports {
    public MonthlyMarketplaceReports(List<? extends IBaseStatistics> groupedFilteredResults, LocalDate month) {
        super(groupedFilteredResults);
        this.month = YearMonth.from(month).toString();
    }

    public MonthlyMarketplaceReports() {
    }

    public MonthlyMarketplaceReports(List<? extends IBaseStatistics> groupedFilteredResults) {
        super(groupedFilteredResults);
    }

}
