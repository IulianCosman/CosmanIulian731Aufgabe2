package org.example.Models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String location;
    private List<Product> products;

    public Customer(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", products=" + products +
                '}';
    }
}
