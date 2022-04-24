package com.wobReporting.server.service;

import com.wobReporting.server.model.Location;
import com.wobReporting.server.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public void saveAll(List<Location> locations) {
        locationRepository.saveAll(locations);
    }
}
