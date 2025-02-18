package org.example.Controllers;

import org.example.Models.Customer;
import org.example.Models.Product;
import org.example.Repositories.CustomerRepo;
import org.example.Repositories.ProductRepo;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public Controller(CustomerRepo customerRepo, ProductRepo productRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    private Customer readCustomer(Scanner scanner) {
        System.out.println("Enter ID, Name, Location:");
        int id = scanner.nextInt();
        scanner.nextLine();
        String name = scanner.nextLine();
        String location = scanner.nextLine();
        return new Customer(id, name, location);
    }

    public void addCustomer(Scanner scanner) {
        customerRepo.add(readCustomer(scanner));
    }

    public void removeCustomer(Scanner scanner) {
        System.out.println("Enter Customer ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        customerRepo.remove(new Customer(id, "", ""));
    }

    public void updateCustomer(Scanner scanner) {
        System.out.println("Enter ID of customer to update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new Name, Location:");
        String name = scanner.nextLine();
        String location = scanner.nextLine();
        List<Customer> customers = customerRepo.getAll();
        for(Customer customer: customers){
            if(customer.getId() == id){
                customerRepo.update(customer, new Customer(id, name, location));
            }
        }

    }

    public void assignProduct(int id, String productName) {
        List<Customer> customers = customerRepo.getAll();
        List<Product> products = productRepo.getAll();

        Product productToAssign = products.stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .orElse(null);

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.addProduct(productToAssign);
                customerRepo.saveAll(customers);
            }
        }

    }

    public void getAllCustomers() {
        List<Customer> customers = customerRepo.getAll();
        customers.forEach(System.out::println);
    }

    private Product readProduct(Scanner scanner) {
        System.out.println("Enter Name, Price, Region:");
        String name = scanner.nextLine();
        double price = scanner.nextDouble();
        scanner.nextLine();
        String region = scanner.nextLine();
        return new Product(name, price, region);
    }

    public void addProduct(Scanner scanner) {
        productRepo.add(readProduct(scanner));
    }

    public void removeProduct(Scanner scanner) {
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        productRepo.remove(new Product(name, 0, ""));
    }

    public void updateProduct(Scanner scanner) {
        System.out.println("Enter old Name:");
        String oldName = scanner.nextLine();
        Product updatedProduct = readProduct(scanner);
        List<Product> products = productRepo.getAll();
        for(Product product: products){
            if(product.getName().equals(oldName)){
                productRepo.update(product, updatedProduct);
                break;
            }
        }
    }

    public void getAllProducts() {
        productRepo.getAll().forEach(System.out::println);
    }

    public void getCustomersByLocation(String location) {
        customerRepo.getAll().stream()
                .filter(c -> c.getLocation().equalsIgnoreCase(location))
                .forEach(System.out::println);
    }

    public void getCustomersByProductRegion(String region) {
        customerRepo.getAll().stream()
                .filter(c -> c.getProducts().stream().anyMatch(p -> p.getRegion().equalsIgnoreCase(region)))
                .sorted()
                .forEach(System.out::println);
    }

}
