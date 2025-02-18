package org.example.View;

import org.example.Controllers.Controller;
import org.example.Repositories.CustomerRepo;
import org.example.Repositories.ProductRepo;

import java.util.Scanner;

public class View {
    private final Controller controller;
    private final Scanner scanner;

    public View(CustomerRepo customerRepo, ProductRepo productRepo) {
        this.controller = new Controller(customerRepo, productRepo);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Product CRUD");
            System.out.println("2. Customer CRUD");
            System.out.println("3. Customers filtered by location");

            System.out.println("6. Assign product to customer");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productCRUD();
                    break;
                case 2:
                    customerCRUD();
                    break;
                case 3:
                    filterCustomersByLocation();
                    break;
                case 6:
                    assignProductToCustomer();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void assignProductToCustomer() {
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Customer Id:");
        int id = scanner.nextInt();
        controller.assignProduct(id, name);
    }

    private void productCRUD() {
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. Show All Products");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                controller.addProduct(scanner);
                break;
            case 2:
                controller.removeProduct(scanner);
                break;
            case 3:
                controller.updateProduct(scanner);
                break;
            case 4:
                controller.getAllProducts();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void customerCRUD() {
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Show All Customers");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                controller.addCustomer(scanner);
                break;
            case 2:
                controller.removeCustomer(scanner);
                break;
            case 3:
                controller.updateCustomer(scanner);
                break;
            case 4:
                controller.getAllCustomers();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void filterCustomersByLocation() {
        System.out.println("Enter location:");
        String location = scanner.nextLine();
        controller.getCustomersByLocation(location);
    }

}