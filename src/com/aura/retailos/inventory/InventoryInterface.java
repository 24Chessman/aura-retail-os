// Aura Retail OS | IT620 | Code Crafters
// Pattern: Proxy
package com.aura.retailos.inventory;

import com.aura.retailos.inventory.composite.Product;

public interface InventoryInterface {

    // Adds a new product to the inventory
    void addProduct(Product p);

    // Removes a product from the inventory by its ID
    void removeProduct(String id);

    // Updates the stock quantity for a given product ID
    void updateStock(String id, int qty);

    // Returns the current stock level for a given product ID
    int getStock(String id);
}
