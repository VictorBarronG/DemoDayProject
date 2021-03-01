package com.tp.dealership.models;

public class Car {
    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private String color;
    private Integer miles;
    private Integer owners;
    private Boolean passedInspec;
    private double price;
    private String vin;

    public Car() { }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getOwners() {
        return owners;
    }

    public void setOwners(Integer owners) {
        this.owners = owners;
    }

    public boolean isPassedInspec() {
        return passedInspec;
    }

    public void setPassedInspec(boolean passedInspec) {
        this.passedInspec = passedInspec;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }
}
