package com.wobReporting.server.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wobReporting.server.model.Marketplace;

import java.io.IOException;

public class MarketplaceDeserializer extends StdDeserializer<Marketplace> {
    public MarketplaceDeserializer() {
        this(null);
    }

    public MarketplaceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Marketplace deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        final Marketplace marketplace = new Marketplace();
        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);

        try {
            marketplace.setId(node.get("id").asLong());

            marketplace.setMarketplaceName(node.get("marketplace_name").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return marketplace;
    }

}
