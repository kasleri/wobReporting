package com.wobReporting.server.repository;

import com.wobReporting.server.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingStatusRepository extends JpaRepository<ListingStatus, Long> {

}
