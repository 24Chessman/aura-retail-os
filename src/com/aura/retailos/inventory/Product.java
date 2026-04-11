// Aura Retail OS | IT620 | Code Crafters
// Supporting class — flat inventory product (used by stubs and pattern classes)
package com.aura.retailos.inventory;

public class Product {

    // Unique identifier for this product
    private String productId;

    // Display name of the product
    private String name;

    // Price of the product in rupees
    private double price;

    // Current stock count
    private int stock;

    // Whether this product needs to be kept refrigerated
    private boolean requiresRefrigeration;

    // Constructs a Product with all required fields
    public Product(String productId, String name, double price,
                   int stock, boolean requiresRefrigeration) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.requiresRefrigeration = requiresRefrigeration;
    }

    // Returns the display name of this product
    public String getName() {
        return name;
    }

    // Returns the price of this product
    public double getPrice() {
        return price;
    }

    // Returns the current stock count
    public int getStock() {
        return stock;
    }

    // Returns true if stock is greater than zero
    public boolean isAvailable() {
        return stock > 0;
    }

    // Decrements stock by 1 when a unit is dispensed
    public void decrementStock() {
        if (stock > 0) {
            stock--;
        }
    }

    // Increments stock by the given quantity when restocked
    public void incrementStock(int qty) {
        stock += qty;
    }

    // Returns the unique product ID
    public String getProductId() {
        return productId;
    }

    // Returns whether this product requires refrigeration
    public boolean isRequiresRefrigeration() {
        return requiresRefrigeration;
    }

    // Returns a concise string representation of this product
    @Override
    public String toString() {
        return "Product[id=" + productId
                + ", name=" + name
                + ", price=" + price
                + ", stock=" + stock + "]";
    }
}
