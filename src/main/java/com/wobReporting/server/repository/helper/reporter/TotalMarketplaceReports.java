package com.wobReporting.server.repository.helper.reporter;

import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;

import java.util.List;

public class TotalMarketplaceReports extends AbstractIMarketplaceReports {
    public TotalMarketplaceReports(List<IBaseStatistics> results) {
        super(results);
    }
}
