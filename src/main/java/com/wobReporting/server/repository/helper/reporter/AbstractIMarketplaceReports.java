package com.wobReporting.server.repository.helper.reporter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.ListingPrice;

import java.util.List;
import java.util.stream.Collectors;

@JsonDeserialize(as = MarketplaceReports.class)
public abstract class AbstractIMarketplaceReports implements IMarketplaceReport {
    protected String marketplaceName;
    protected double totalListingCount;
    protected List<ListingPrice> sumListingPrice;
    protected List<ListingPrice> averageListingPrice;
    protected String month;

    public AbstractIMarketplaceReports() {
    }

    protected AbstractIMarketplaceReports(List<? extends IBaseStatistics> groupedFilteredResults) {
        this.totalListingCount = groupedFilteredResults.stream()
                .mapToDouble(IBaseStatistics::getCount)
                .sum();
        marketplaceName = groupedFilteredResults.get(0).getMarketplaceName();

        sumListingPrice = groupedFilteredResults.stream()
                .map(x -> new ListingPrice(x.getPriceSum(), x.getCurrency()))
                .collect(Collectors.toList());

        averageListingPrice = groupedFilteredResults.stream()
                .map(x -> new ListingPrice(x.getPriceAvg(), x.getCurrency()))
                .collect(Collectors.toList());

    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }

    public void setTotalListingCount(double totalListingCount) {
        this.totalListingCount = totalListingCount;
    }

    public void setSumListingPrice(List<ListingPrice> sumListingPrice) {
        this.sumListingPrice = sumListingPrice;
    }

    public void setAverageListingPrice(List<ListingPrice> averageListingPrice) {
        this.averageListingPrice = averageListingPrice;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public double getTotalListingCount() {
        return totalListingCount;
    }

    public List<ListingPrice> getSumListingPrice() {
        return sumListingPrice;
    }

    public List<ListingPrice> getAverageListingPrice() {
        return averageListingPrice;
    }

    public String getMonth() {
        return month;
    }

}
