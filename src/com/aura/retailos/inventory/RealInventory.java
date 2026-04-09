// Aura Retail OS | IT620 | Code Crafters
// Pattern: Proxy
package com.aura.retailos.inventory;

import com.aura.retailos.inventory.composite.Product;
import java.util.HashMap;
import java.util.Map;

public class RealInventory implements InventoryInterface {

    // Maps product ID to its current stock quantity
    private Map<String, Integer> stock;

    // Maps product ID to the full Product object
    private Map<String, Product> products;

    // Adds a product and initialises its stock count
    @Override
    public void addProduct(Product p) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Removes a product and its stock entry by product ID
    @Override
    public void removeProduct(String id) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Updates the stock quantity for the product with the given ID
    @Override
    public void updateStock(String id, int qty) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the stock count for the product with the given ID
    @Override
    public int getStock(String id) {
        throw new UnsupportedOperationException("To be implemented");
    }
}
