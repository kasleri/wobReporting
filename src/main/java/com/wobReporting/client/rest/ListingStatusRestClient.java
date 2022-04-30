package com.wobReporting.client.rest;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wobReporting.server.deserializer.ListingStatusDeserializer;
import com.wobReporting.server.deserializer.ObjectMapperResolver;
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
    protected ObjectMapperResolver getCustomDeserializer() {
        SimpleModule module = new SimpleModule("ListingStatusDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ListingStatus.class, new ListingStatusDeserializer());
        return new ObjectMapperResolver(module);
    }

    @Override
    protected GenericType<List<ListingStatus>> getEntityType() {
        return new GenericType<List<ListingStatus>>() {
        };
    }
}
