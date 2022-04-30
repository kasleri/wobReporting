package com.wobReporting.server.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wobReporting.server.model.Location;

import java.io.IOException;
import java.util.UUID;

public class LocationDeserializer extends StdDeserializer<Location> {


    public LocationDeserializer() {
        this(null);
    }

    public LocationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Location deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Location location = new Location();
        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);

        try {
            location.setId(UUID.fromString(node.get("id").asText()));

            location.setManagerName(node.get("manager_name").asText());

            location.setPhone(node.get("phone").asText());

            location.setAddressPrimary(node.get("address_primary").asText());

            location.setAddressSecondary(node.get("address_secondary").asText());

            location.setCountry(node.get("country").asText());

            location.setTown(node.get("town").asText());

            location.setPostalCode(node.get("postal_code").asText());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

}
