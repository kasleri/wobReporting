package com.wobReporting.server.repository;

import com.wobReporting.server.model.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, Long> {
    @Query("SELECT m FROM Marketplace m WHERE m.marketplace_name = ?1")
    Optional<Marketplace> findByMarketplaceName(String name);
}
