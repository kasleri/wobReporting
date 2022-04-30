package com.wobReporting.client.rest;

import com.wobReporting.server.deserializer.ObjectMapperResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
public abstract class AbstractRestClient<T> {
    @Value("${rest.uri}")
    private String restUri;
    @Value("${rest.key}")
    private String key;
    final Client client = ClientBuilder.newClient().register(getCustomDeserializer());

    public List<T> getJson() {
        return this.client

                .target(restUri)
                .path(getPath())
                .queryParam("key", key)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(getEntityType());
    }

    protected abstract String getPath();

    protected abstract ObjectMapperResolver getCustomDeserializer();

    protected abstract <E> GenericType<List<E>> getEntityType();
}
