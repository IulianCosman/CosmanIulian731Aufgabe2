package org.example;

import org.example.Repositories.CustomerRepo;
import org.example.Repositories.ProductRepo;
import org.example.View.View;

public class Main {
    public static void main(String[] args) {
        CustomerRepo customerRepo = new CustomerRepo("customers.txt");
        ProductRepo productRepo = new ProductRepo("products.txt");
        new View(customerRepo, productRepo).start();
        }
    }
