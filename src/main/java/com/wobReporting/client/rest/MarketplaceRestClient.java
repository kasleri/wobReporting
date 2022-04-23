package com.wobReporting.client.rest;

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
    protected GenericType<List<Marketplace>> getEntityType() {
        return new GenericType<List<Marketplace>>() {
        };
    }


}
