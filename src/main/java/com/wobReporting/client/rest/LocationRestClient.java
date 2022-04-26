package com.wobReporting.client.rest;

import com.wobReporting.server.model.Location;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.GenericType;
import java.util.List;

@Component("LocationRestClient")
public class LocationRestClient extends AbstractRestClient<Location> {

    protected static final String PATH = "location";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected GenericType<List<Location>> getEntityType() {
        return new GenericType<List<Location>>() {
        };
    }
}
