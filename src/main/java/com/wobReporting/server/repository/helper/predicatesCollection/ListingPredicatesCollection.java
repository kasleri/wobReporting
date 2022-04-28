package com.wobReporting.server.repository.helper.predicatesCollection;

import com.wobReporting.server.model.Marketplace;
import com.wobReporting.server.service.MarketplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ListingPredicatesCollection {

    @Autowired
    private MarketplaceService marketplaceService;

    private static MarketplaceService staticMarketplaceService;


    @PostConstruct
    private void initStaticMarketplaceService() {
        staticMarketplaceService = this.marketplaceService;
    }

    public static List<SearchCriteria> getEbayListingPredicates() {
        Optional<Marketplace> marketplace = staticMarketplaceService.findByMarketplaceName("EBAY");
        List<SearchCriteria> predicates = new ArrayList<>();
        marketplace.ifPresent(value -> predicates.add(new SearchCriteria("marketplace", ":", value.getId())));
        return predicates;
    }

    public static List<SearchCriteria> getAmazonListingPredicates() {
        Optional<Marketplace> marketplace = staticMarketplaceService.findByMarketplaceName("AMAZON");
        List<SearchCriteria> predicates = new ArrayList<>();
        marketplace.ifPresent(value -> predicates.add(new SearchCriteria("marketplace", ":", value.getId())));
        return predicates;
    }

    public static List<SearchCriteria> getTotalListingCountPredicates() {
        ArrayList<SearchCriteria> predicates = new ArrayList<>();
        predicates.add(new SearchCriteria("title", ":", "'ahakea"));
        return predicates;
    }
}
