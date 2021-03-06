package com.wobReporting.server.service;

import com.wobReporting.server.model.Marketplace;
import com.wobReporting.server.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketplaceService {
    @Autowired
    private MarketplaceRepository marketplaceRepository;

    public void saveAll(List<Marketplace> marketplaces) {
        marketplaceRepository.saveAll(marketplaces);
    }

    public Optional<Marketplace> findById(long id) {
        return marketplaceRepository.findById(id);
    }

    public Optional<Marketplace> findByMarketplaceName(String name) {
        return marketplaceRepository.findByMarketplaceName(name);
    }

    public List<Marketplace> findAll() {
        return marketplaceRepository.findAll();
    }
}
