package com.wobReporting.server.service;

import com.wobReporting.server.model.Marketplace;
import com.wobReporting.server.repository.MarketplaceRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
