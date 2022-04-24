package com.wobReporting.client.rest;

import com.wobReporting.server.model.ListingStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.GenericType;
import java.util.List;

@Component("ListingStatusRestClient")
public class ListingStatusRestClient extends AbstractRestClient<ListingStatus> {

    protected static final String PATH = "listingStatus";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected GenericType<List<ListingStatus>> getEntityType() {
        return new GenericType<List<ListingStatus>>() {
        };
    }
}
