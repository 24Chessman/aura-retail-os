// Aura Retail OS | IT620 | Code Crafters
// Pattern: Composite
package com.aura.retailos.inventory.composite;

public class Product implements InventoryItem {

    // Unique identifier for this product
    private String itemId;

    // Display name of the product
    private String name;

    // Base price of the product
    private double price;

    // Current stock count for this product
    private int stock;

    // Whether this product requires refrigeration to remain available
    private boolean requiresRefrigeration;

    // Returns the display name of this product
    @Override
    public String getName() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the price of this product
    @Override
    public double getPrice() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns true if the product has stock and its refrigeration requirements are met
    @Override
    public boolean isAvailable() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the unique item ID of this product
    @Override
    public String getItemId() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the current stock quantity
    public int getStock() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Sets the stock quantity directly
    public void setStock(int qty) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Decrements stock by 1 when a unit is dispensed
    public void decrementStock() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Increments stock by the specified quantity when restocked
    public void incrementStock(int qty) {
        throw new UnsupportedOperationException("To be implemented");
    }
}
