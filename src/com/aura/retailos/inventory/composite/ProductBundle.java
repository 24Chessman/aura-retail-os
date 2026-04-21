// Aura Retail OS | IT620 | Code Crafters
// Pattern: Composite
package com.aura.retailos.inventory.composite;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProductBundle implements InventoryItem {

    // Unique identifier for this bundle
    private String itemId;

    // Display name of the bundle
    private String name;

    // Child items within this bundle (can be Products or nested ProductBundles)
    private List<InventoryItem> children = new ArrayList<>();

    // Constructor
    public ProductBundle(String itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    // Returns the display name of this bundle
    @Override
    public String getName() {
        return name;
    }

    // Returns the total price as the sum of all children's prices
    @Override
    public double getPrice() {
        double total = 0;
        for (InventoryItem item : children) {
            total += item.getPrice();
        }
        return total;
    }

    // Returns true only if every child item is available (recursive check)
    @Override
    public boolean isAvailable() {
        return children.stream().allMatch(InventoryItem::isAvailable);
    }

    // Returns the unique item ID of this bundle
    @Override
    public String getItemId() {
        return itemId;
    }

    // Adds a child item (product or bundle) to this bundle
    public void addItem(InventoryItem item) {
        children.add(item);
        System.out.println("[BUNDLE] Added " + item.getName() + " to " + name);
    }

    // Removes a child item by its ID from this bundle
    public void removeItem(String id) {
        children.removeIf(item -> item.getItemId().equals(id));
        System.out.println("[BUNDLE] Removed item " + id + " from " + name);
    }

    // Returns an unmodifiable view of the children list
    public List<InventoryItem> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String toString() {
        return "Bundle[" + name + "] contains " + children.size() + " items"
                + " | Total price: " + getPrice() + " | Available: " + isAvailable();
    }
}
