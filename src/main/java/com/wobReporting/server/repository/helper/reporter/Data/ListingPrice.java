package com.wobReporting.server.repository.helper.reporter.Data;

public class ListingPrice {
    private double price;
    private String currency;

    public ListingPrice() {
    }

    public ListingPrice(double price, String currency) {
        this.price = price;
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
