// Aura Retail OS | IT620 | Code Crafters
// Pattern: Proxy
package com.aura.retailos.inventory;

import com.aura.retailos.inventory.composite.Product;
import java.util.HashMap;
import java.util.Map;

public class RealInventory implements InventoryInterface {

    // Maps product ID to its current stock quantity
    private Map<String, Integer> stock = new HashMap<>();

    // Maps product ID to the full Product object
    private Map<String, Product> products = new HashMap<>();

    // Adds a product and initialises its stock count
    @Override
    public void addProduct(Product p) {
        products.put(p.getItemId(), p);
        stock.put(p.getItemId(), p.getStock());
        System.out.println("[INVENTORY] Product added: " + p.getName() + " | Stock: " + p.getStock());
    }

    // Removes a product and its stock entry by product ID
    @Override
    public void removeProduct(String id) {
        products.remove(id);
        stock.remove(id);
        System.out.println("[INVENTORY] Product removed: " + id);
    }

    // Updates the stock quantity for the product with the given ID
    @Override
    public void updateStock(String id, int qty) {
        stock.put(id, qty);
        System.out.println("[INVENTORY] Stock updated: " + id + " -> " + qty + " units");
    }

    // Returns the stock count for the product with the given ID
    @Override
    public int getStock(String id) {
        return stock.getOrDefault(id, 0);
    }

    // Returns the full Product object for the given ID, or null if not found
    public Product getProduct(String id) {
        return products.get(id);
    }

    // Returns all products currently held in the inventory
    public java.util.Collection<Product> getAllProducts() {
        return products.values();
    }

    // Prints all products with their current stock levels
    public void printInventory() {
        System.out.println("[INVENTORY] ===== Current Inventory =====");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String id = entry.getKey();
            Product p = entry.getValue();
            int currentStock = stock.getOrDefault(id, 0);
            System.out.println("[INVENTORY]   " + p.getName() + " (id=" + id + ") | Stock: " + currentStock);
        }
        System.out.println("[INVENTORY] ==================================");
    }
}
