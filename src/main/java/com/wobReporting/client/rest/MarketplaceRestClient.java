package com.wobReporting.client.rest;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wobReporting.server.deserializer.MarketplaceDeserializer;
import com.wobReporting.server.deserializer.ObjectMapperResolver;
import com.wobReporting.server.model.Marketplace;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.GenericType;
import java.util.List;

@Component("MarketplaceRestClient")
public class MarketplaceRestClient extends AbstractRestClient<Marketplace> {

    protected static final String PATH = "marketplace";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected ObjectMapperResolver getCustomDeserializer() {
        SimpleModule module = new SimpleModule("MarketplaceDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Marketplace.class, new MarketplaceDeserializer());

        return new ObjectMapperResolver(module);
    }

    @Override
    protected GenericType<List<Marketplace>> getEntityType() {
        return new GenericType<List<Marketplace>>() {
        };
    }


}
