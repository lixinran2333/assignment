package com.assignment.rest.Models;


import jakarta.persistence.*;

@Table(name = "stock_record")
@Entity(name = "stock_record")
public class StockRecord {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setQuote_currency(String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public void setQuote_currency_amount(double quote_currency_amount) {
        this.quote_currency_amount = quote_currency_amount;
    }

    public String getStatus() {
        return status;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public double getQuote_currency_amount() {
        return quote_currency_amount;
    }

    @Column
    private String status;
    @Column
    private String quote_currency;
    @Column
    private double quote_currency_amount;


}
