package com.wobReporting.server.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wobReporting.server.model.ListingStatus;

import java.io.IOException;

public class ListingStatusDeserializer extends StdDeserializer<ListingStatus> {
    public ListingStatusDeserializer() {
        this(null);
    }

    public ListingStatusDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ListingStatus deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        final ListingStatus listingStatus = new ListingStatus();
        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);

        try {
            listingStatus.setId(node.get("id").asInt());

            listingStatus.setStatusName(node.get("status_name").asText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listingStatus;
    }

}
