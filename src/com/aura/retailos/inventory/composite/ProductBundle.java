// Aura Retail OS | IT620 | Code Crafters
// Pattern: Composite
package com.aura.retailos.inventory.composite;

import java.util.List;
import java.util.ArrayList;

public class ProductBundle implements InventoryItem {

    // Unique identifier for this bundle
    private String itemId;

    // Display name of the bundle
    private String name;

    // Child items within this bundle (can be Products or nested ProductBundles)
    private List<InventoryItem> children;

    // Returns the display name of this bundle
    @Override
    public String getName() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the total price as the sum of all children's prices
    @Override
    public double getPrice() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns true only if every child item is available (recursive check)
    @Override
    public boolean isAvailable() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the unique item ID of this bundle
    @Override
    public String getItemId() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Adds a child item (product or bundle) to this bundle
    public void addItem(InventoryItem item) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Removes a child item by its ID from this bundle
    public void removeItem(String id) {
        throw new UnsupportedOperationException("To be implemented");
    }
}
