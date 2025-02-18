package org.example.Repositories;

import org.example.Models.Customer;
import org.example.Models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo implements Repository<Customer> {
    private final String filePath;

    public CustomerRepo(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void add(Customer customer) {
        List<Customer> customers = getAll();
        customers.add(customer);
        saveAll(customers);
    }

    @Override
    public void remove(Customer customer) {
        List<Customer> customers = getAll();
        customers.removeIf(c -> c.getId() == customer.getId());
        saveAll(customers);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String[] customerInfo = parts[0].split(",");
                Customer customer = new Customer(Integer.parseInt(customerInfo[0]), customerInfo[1], customerInfo[2]);

                // If products exist, load them
                for (int i = 1; i < parts.length; i++) {
                    String[] productInfo = parts[i].split(":");
                    if (productInfo.length == 3) {
                        Product product = new Product(productInfo[0], Double.parseDouble(productInfo[1]), productInfo[2]);
                        customer.addProduct(product);
                    }
                }
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer, Customer updatedCustomer) {
        List<Customer> customers = getAll();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
        saveAll(customers);
    }

    public void addProduct(Customer customer, Product product) {
        List<Customer> customers = getAll();
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.addProduct(product);
                break;
            }
        }
        saveAll(customers);
    }

    public void saveAll(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Customer customer : customers) {
                StringBuilder sb = new StringBuilder();
                sb.append(customer.getId()).append(",").append(customer.getName()).append(",").append(customer.getLocation());

                // Append products if available
                for (Product product : customer.getProducts()) {
                    sb.append(";").append(product.getName()).append(":").append(product.getPrice()).append(":").append(product.getRegion());
                }

                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
