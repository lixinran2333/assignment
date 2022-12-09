package com.assignment.rest.Models;

public class SSGroupByType {
    private String type;
    private double min;
    private double max;
    private double avg;

    private String quoteCurrency;
    public SSGroupByType(String type, double min, double max, double avg) {
        this.type = type;
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "type = " + type + "has min " + min + " max " + max + " avg " + avg;
    }
}
