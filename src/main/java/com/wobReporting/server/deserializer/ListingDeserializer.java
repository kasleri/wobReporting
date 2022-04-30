package com.wobReporting.server.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.wobReporting.server.model.Listing;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ListingDeserializer extends StdDeserializer<Listing> {


    public ListingDeserializer() {
        this(null);
    }

    public ListingDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Listing deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {

        Listing listing = new Listing();

        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);

        try {
            listing.setId(UUID.fromString(node.get("id").asText()));

            listing.setTitle(node.get("title").asText());

            listing.setDescription(node.get("description").asText());

            listing.setLocationId(UUID.fromString(node.get("location_id").asText()));

            final Double listingListingPrice = node.get("listing_price").asDouble();
            listing.setListingPrice(listingListingPrice.floatValue());

            final String listingCurrency = node.get("currency").asText();
            listing.setCurrency(node.get("currency").asText());

            final Integer listingQuantity = node.get("quantity").asInt();
            listing.setQuantity(node.get("quantity").asInt());

            listing.setMarketplace(node.get("marketplace").asInt());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            JsonNode uploadTimeNode = node.get("upload_time");
            LocalDate date;
            if (!uploadTimeNode.isNull()) {
                date = LocalDate.parse(uploadTimeNode.asText(), formatter);

            } else {
                date = null;
            }

            listing.setUploadTime(date);

            listing.setOwnerEmailAddress(node.get("owner_email_address").asText());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return listing;
    }

}
