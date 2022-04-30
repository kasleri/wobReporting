package com.wobReporting.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
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
    @Column(name = "location_id")
    private UUID locationId;
    @NotNull
    @Digits(message = "Max decimals 2", integer = 6, fraction = 2)
    @Min(message = "listing_price must be greater than 0", value = 0)
    @Column(name = "listing_price")
    private Float listingPrice;
    @NotNull
    @Size(max = 3)
    private String currency;
    @NotNull
    @Min(message = "quantity must be greater than 0", value = 0)
    private Integer quantity;
    @NotNull
    @Min(message = "listing_status must greater than 0", value = 1)
    @Column(name = "listing_status")
    private Integer listingStatus;
    @NotNull
    private Integer marketplace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @NotNull
    @Column(name = "upload_time")
    public LocalDate uploadTime;
    @Email(message = "Email not valid")
    @Column(name = "owner_email_address")
    private String ownerEmailAddress;

    public Listing() {
    }

    public Listing(UUID id, String title, String description, UUID locationId, Float listingPrice, String currency, Integer quantity, Integer listingStatus, Integer marketplace, LocalDate uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.locationId = locationId;
        this.listingPrice = listingPrice;
        this.currency = currency;
        this.quantity = quantity;
        this.listingStatus = listingStatus;
        this.marketplace = marketplace;
        this.uploadTime = uploadTime;
        this.ownerEmailAddress = ownerEmailAddress;
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

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public Float getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Float listingPrice) {
        this.listingPrice = listingPrice;
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

    public Integer getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(Integer listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Integer marketplace) {
        this.marketplace = marketplace;
    }

    public @NotNull LocalDate getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDate uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }
}
