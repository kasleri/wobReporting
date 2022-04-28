
package com.wobReporting.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.UUID;

@XmlRootElement
@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @NotNull
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private UUID location_id;
    @NotNull
    @Digits(message = "Max decimals 2", integer = 6, fraction = 2)
    @Min(message = "listing_price must be greater than 0", value = 0)
    private Float listing_price;
    @NotNull
    @Size(max = 3)
    private String currency;
    @NotNull
    @Min(message = "quantity must be greater than 0", value = 0)
    private Integer quantity;
    @NotNull
    @Min(message = "listing_status must greater than 0", value = 1)
    private Integer listing_status;
    @NotNull
    private Integer marketplace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @NotNull
    public Date upload_time;
    @Email(message = "Email not valid")
    private String owner_email_address;

    public Listing() {
    }

    public Listing(UUID id, String title, String description, UUID location_id, Float listing_price, String currency, Integer quantity, Integer listing_status, Integer marketplace, Date upload_time, String owner_email_address) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location_id = location_id;
        this.listing_price = listing_price;
        this.currency = currency;
        this.quantity = quantity;
        this.listing_status = listing_status;
        this.marketplace = marketplace;
        this.upload_time = upload_time;
        this.owner_email_address = owner_email_address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public Float getListing_price() {
        return listing_price;
    }

    public void setListing_price(Float listing_price) {
        this.listing_price = listing_price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getListing_status() {
        return listing_status;
    }

    public void setListing_status(Integer listing_status) {
        this.listing_status = listing_status;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Integer marketplace) {
        this.marketplace = marketplace;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public String getOwner_email_address() {
        return owner_email_address;
    }

    public void setOwner_email_address(String owner_email_address) {
        this.owner_email_address = owner_email_address;
    }
}
