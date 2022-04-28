package com.wobReporting.server.repository.helper.reporter.Data;

public interface IBaseStatistics {
    public String getMarketplaceName();

    public double getPriceSum();

    public int getCount();

    public double getPriceAvg();

    public String getCurrency();
}
