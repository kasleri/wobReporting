package com.wobReporting.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "marketplaces")
public class Marketplace {
    @Id
    private Long id;
    private String marketplace_name;

    public Marketplace() {
    }

    public Marketplace(Long id, String marketplace_name) {
        this.id = id;
        this.marketplace_name = marketplace_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketplace_name() {
        return marketplace_name;
    }

    public void setMarketplace_name(String marketplace_name) {
        this.marketplace_name = marketplace_name;
    }
}
