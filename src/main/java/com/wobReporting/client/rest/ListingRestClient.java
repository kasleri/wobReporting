package com.wobReporting.client.rest;

import com.wobReporting.server.model.Listing;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.GenericType;
import java.util.List;

@Component("ListingRestClient")
public class ListingRestClient extends AbstractRestClient<Listing> {

    protected static final String PATH = "listing";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected GenericType<List<Listing>> getEntityType() {
        return new GenericType<List<Listing>>() {
        };
    }
}
