package com.wobReporting.server.repository;

import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.helper.predicatesCollection.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepositoryCustom {

    List<Listing> findByPredicates(List<SearchCriteria> params);

}
