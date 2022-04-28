package com.wobReporting.server.repository;

import com.wobReporting.server.model.Listing;
import com.wobReporting.server.repository.helper.predicatesCollection.ListingSearchCriteria;
import com.wobReporting.server.repository.helper.predicatesCollection.SearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ListingRepositoryImpl implements ListingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Listing> findByPredicates(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Listing> query = builder.createQuery(Listing.class);
        Root r = query.from(Listing.class);

        Predicate predicate = builder.conjunction();

        ListingSearchCriteria searchConsumer =
                new ListingSearchCriteria(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        List<Listing> result = entityManager.createQuery(query).getResultList();
        return result;
    }
}
