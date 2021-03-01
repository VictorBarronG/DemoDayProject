package com.tp.dealership.controllers;

public class SearchfilterParameters {
    Integer yearStart; //range year start
    Integer yearEnd;   //range year end
    String make;
    String model ;
    Integer miles; //miles less then
    String color;
    Integer owners;
    Boolean passinspec;
    Integer priceStart;
    Integer priceEnd;

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Integer getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(Integer yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOwners() {
        return owners;
    }

    public void setOwners(Integer owners) {
        this.owners = owners;
    }

    public Boolean getPassinspec() {
        return passinspec;
    }

    public void setPassinspec(Boolean passinspec) {
        this.passinspec = passinspec;
    }

    public Integer getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(Integer priceStart) {
        this.priceStart = priceStart;
    }

    public Integer getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(Integer priceEnd) {
        this.priceEnd = priceEnd;
    }
}
