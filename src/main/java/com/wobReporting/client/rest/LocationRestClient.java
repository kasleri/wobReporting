package com.wobReporting.client.rest;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wobReporting.server.deserializer.LocationDeserializer;
import com.wobReporting.server.deserializer.ObjectMapperResolver;
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
    protected ObjectMapperResolver getCustomDeserializer() {
        SimpleModule module = new SimpleModule("LocationDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Location.class, new LocationDeserializer());
        return new ObjectMapperResolver(module);
    }

    @Override
    protected GenericType<List<Location>> getEntityType() {
        return new GenericType<List<Location>>() {
        };
    }
}
