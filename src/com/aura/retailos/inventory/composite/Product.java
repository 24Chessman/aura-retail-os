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

    // Full constructor
    public Product(String itemId, String name, double price, int stock, boolean requiresRefrigeration) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.requiresRefrigeration = requiresRefrigeration;
    }

    // Returns the display name of this product
    @Override
    public String getName() {
        return name;
    }

    // Returns the price of this product
    @Override
    public double getPrice() {
        return price;
    }

    // Returns true if the product has stock available
    @Override
    public boolean isAvailable() {
        return stock > 0;
    }

    // Returns the unique item ID of this product
    @Override
    public String getItemId() {
        return itemId;
    }

    // Returns the current stock quantity
    public int getStock() {
        return stock;
    }

    // Sets the stock quantity directly
    public void setStock(int qty) {
        this.stock = qty;
    }

    // Decrements stock by 1 when a unit is dispensed
    public void decrementStock() {
        if (stock > 0) {
            stock--;
            System.out.println("[PRODUCT] Stock decremented: " + name + " now " + stock + " units");
        }
    }

    // Increments stock by the specified quantity when restocked
    public void incrementStock(int qty) {
        stock += qty;
        System.out.println("[PRODUCT] Stock incremented: " + name + " now " + stock + " units");
    }

    // Returns whether this product requires refrigeration
    public boolean requiresRefrigeration() {
        return requiresRefrigeration;
    }

    @Override
    public String toString() {
        return "Product[id=" + itemId + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
    }
}
