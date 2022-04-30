package com.wobReporting.server.model;

import javax.persistence.Column;
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
    @Column(name = "marketplace_name")
    private String marketplaceName;

    public Marketplace() {
    }

    public Marketplace(Long id, String marketplaceName) {
        this.id = id;
        this.marketplaceName = marketplaceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }
}
