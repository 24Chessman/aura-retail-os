// Aura Retail OS | IT620 | Code Crafters
// Pattern: Composite
package com.aura.retailos.inventory.composite;

public interface InventoryItem {

    // Returns the display name of this item (product or bundle)
    String getName();

    // Returns the price of this item; for bundles, the sum of all children
    double getPrice();

    // Returns whether this item is available; for bundles, all children must be available
    boolean isAvailable();

    // Returns the unique identifier of this item
    String getItemId();
}
