package com.wobReporting.server.repository;

import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.helper.reporter.Data.IBaseStatistics;
import com.wobReporting.server.repository.helper.reporter.Data.IBestLister;
import com.wobReporting.server.repository.helper.reporter.Data.MonthlyIBaseStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    @Query(value = "SELECT mode() within group (order by owner_email_address) FROM listings",
            nativeQuery = true)
    String getTotalBestLister();

    @Query(value = "SELECT date_trunc('month', upload_time) AS month, " +
            "mode() within group (order by owner_email_address) as bestLister " +
            "FROM listings " +
            "GROUP BY month " +
            "ORDER BY month ",
            nativeQuery = true)
    List<IBestLister> getMonthlyBestLister();

    @Query(nativeQuery = true,
            value = " SELECT date_trunc('month', upload_time) AS actMonth, " +
                    "m.marketplace_name AS marketplaceName, " +
                    "sum(l.listing_price) as priceSum, " +
                    "sum(l.quantity) as count, " +
                    "avg(l.listing_price) as priceAvg, " +
                    "l.currency as currency " +
                    "FROM listings l " +
                    "LEFT JOIN marketplaces m ON m.id = l.marketplace " +
                    "GROUP BY actMonth, m.marketplace_name, l.currency " +
                    "ORDER BY actMonth")
    List<MonthlyIBaseStatistics> getMonthlyStatistics();

    @Query(nativeQuery = true,
            value = "SELECT " +
                    "m.marketplace_name AS marketplaceName, " +
                    "sum(l.listing_price) as priceSum, " +
                    "sum(l.quantity) as count, " +
                    "avg(l.listing_price) as priceAvg, " +
                    "l.currency as currency " +
                    "FROM listings l " +
                    "LEFT JOIN marketplaces m ON m.id = l.marketplace " +
                    "GROUP BY m.marketplace_name, l.currency"
    )
    List<IBaseStatistics> getTotalStatistics();

}
