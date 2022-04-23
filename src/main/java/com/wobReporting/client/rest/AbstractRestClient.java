package com.wobReporting.client.rest;

import com.wobReporting.config.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public abstract class AbstractRestClient<T> {

    static final String REST_URI = PropertiesLoader.getProperty("rest.uri");
    static final String KEY = PropertiesLoader.getProperty("rest.key");
    final Client client = ClientBuilder.newClient();

    public List<T> getJson() {
        return this.client
                .target(REST_URI)
                .path(getPath())
                .queryParam("key", KEY)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(getEntityType());
    }

    protected abstract String getPath();

    protected abstract <E> GenericType<List<E>> getEntityType();
}
