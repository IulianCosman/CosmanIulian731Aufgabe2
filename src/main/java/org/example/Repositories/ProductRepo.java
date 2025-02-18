package org.example.Repositories;

import org.example.Models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo implements Repository<Product> {
    private final String filePath;

    public ProductRepo(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void add(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(product.getName() + "," + product.getPrice() + "," + product.getRegion() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Product product) {
        List<Product> products = getAll();
        products.removeIf(p -> p.getName().equals(product.getName()));
        saveAll(products);
    }

    @Override
    public void update(Product product, Product updatedProduct){
        remove(product);
        add(updatedProduct);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                products.add(new Product(parts[0], Double.parseDouble(parts[1]), parts[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void saveAll(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getRegion() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
