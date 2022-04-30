package com.wobReporting.client.rest;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wobReporting.server.deserializer.ListingDeserializer;
import com.wobReporting.server.deserializer.ObjectMapperResolver;
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
    protected ObjectMapperResolver getCustomDeserializer() {
        SimpleModule module = new SimpleModule("ListingDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Listing.class, new ListingDeserializer());
        return new ObjectMapperResolver(module);
    }

    @Override
    protected GenericType<List<Listing>> getEntityType() {
        return new GenericType<List<Listing>>() {
        };
    }
}
