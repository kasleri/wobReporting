package com.wobReporting.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@Entity
@Table(name = "locations")
public class Location {
    @Id
    private UUID id;
    private String manager_name;
    private String phone;
    private String address_primary;
    private String address_secondary;
    private String country;
    private String town;
    private String postal_code;

    @Transient
    @OneToMany(mappedBy = "locations", cascade = CascadeType.ALL)
    private Set<Listing> listings;

    public Location() {
    }

    public Location(UUID id, String manager_name, String phone, String address_primary, String address_secondary, String country, String town, String postal_code) {
        this.id = id;
        this.manager_name = manager_name;
        this.phone = phone;
        this.address_primary = address_primary;
        this.address_secondary = address_secondary;
        this.country = country;
        this.town = town;
        this.postal_code = postal_code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_primary() {
        return address_primary;
    }

    public void setAddress_primary(String address_primary) {
        this.address_primary = address_primary;
    }

    public String getAddress_secondary() {
        return address_secondary;
    }

    public void setAddress_secondary(String address_secondary) {
        this.address_secondary = address_secondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
