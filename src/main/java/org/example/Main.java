package org.example;

import org.example.Models.Customer;
import org.example.Models.Product;
import org.example.Repositories.CustomerRepo;
import org.example.Repositories.ProductRepo;
import org.example.View.View;

public class Main {
    public static void main(String[] args) {
        CustomerRepo customerRepo = new CustomerRepo("customers.txt");
        ProductRepo productRepo = new ProductRepo("products.txt");
        productRepo.add(new Product("Kunai", 50.0, "Konoha"));
        productRepo.add(new Product("Shuriken", 30.0, "Konoha"));
        productRepo.add(new Product("Schwert", 200.0, "Kirigakure"));
        productRepo.add(new Product("Heiltrank", 100.0, "Sunagakure"));
        productRepo.add(new Product("Sprengsiegel", 75.0, "Iwagakure"));
        productRepo.add(new Product("Riesenfächer", 300.0, "Sunagakure"));
        productRepo.add(new Product("Giftklinge", 150.0, "Kirigakure"));
        productRepo.add(new Product("Explosionskugel", 250.0, "Iwagakure"));
        productRepo.add(new Product("Schattendolch", 180.0, "Konoha"));
        productRepo.add(new Product("Wasserperle", 90.0, "Kirigakure"));
        View view =   new View(customerRepo, productRepo);
        Customer c1 = new Customer(1, "Naruto Uzumaki", "Konoha");
        view.assignProductToCustomer(1, productRepo.get(0)); // Kunai
        view.assignProductToCustomer(1, productRepo.get(3)); // Heiltrank
        view.assignProductToCustomer(1, productRepo.get(8)); // Schattendolch
        view.assignProductToCustomer(1, productRepo.get(5)); // Riesenfächer
        Customer c2 = new Customer(2, "Gaara", "Sunagakure");
        view.assignProductToCustomer(2, productRepo.get(2)); // Schwert
        view.assignProductToCustomer(2, productRepo.get(4)); // Sprengsiegel
        view.assignProductToCustomer(2, productRepo.get(6)); // Giftklinge
        view.assignProductToCustomer(2, productRepo.get(1)); // Shuriken
        Customer c3 = new Customer(3, "Kisame Hoshigaki", "Kirigakure");
        view.assignProductToCustomer(3,productRepo.get(1)); // Shuriken
        view.assignProductToCustomer(3,productRepo.get(2)); // Schwert
        view.assignProductToCustomer(3,productRepo.get(3)); // Heiltrank
        view.assignProductToCustomer(3,productRepo.get(7)); // Explosionskugel
        view.assignProductToCustomer(3,productRepo.get(9)); // Wasserperle
        Customer c4 = new Customer(4, "Deidara", "Iwagakure");
        view.assignProductToCustomer(4,productRepo.get(0)); // Kunai
        view.assignProductToCustomer(4,productRepo.get(4)); // Sprengsiegel
        view.assignProductToCustomer(4,productRepo.get(7)); // Explosionskugel
        view.assignProductToCustomer(4,productRepo.get(9)); // Wasserperle
        Customer c5 = new Customer(5, "Itachi Uchiha", "Konoha");
        view.assignProductToCustomer(5,productRepo.get(8)); // Schattendolch
        view.assignProductToCustomer(5,productRepo.get(6)); // Giftklinge
        view.assignProductToCustomer(5,productRepo.get(2)); // Schwert
        view.assignProductToCustomer(5,productRepo.get(7)); // Explosionskugel
        customerRepo.add(c1);
        customerRepo.add(c2);
        customerRepo.add(c3);
        customerRepo.add(c4);
        customerRepo.add(c5);
        view.start();
        }
    }
