package org.example.Models;

public class Product {
    private String name;
    private double price;
    private String region;

    public Product(String name, double price, String region){
        this.name = name;
        this.price = price;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getRegion() {
        return region;
    }


    @Override
    public String toString() {
        return name + " ($" + price + ", " + region + ")";
    }
}
