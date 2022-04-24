package com.wobReporting.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name = "listing_status")
public class ListingStatus {
    @Id
    private Integer id;
    private String status_name;

    @Transient
    @OneToMany(mappedBy = "listing_status", cascade = CascadeType.ALL)
    private Set<Listing> listings;

    public ListingStatus() {
    }

    public ListingStatus(Integer id, String status_name) {
        this.id = id;
        this.status_name = status_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
