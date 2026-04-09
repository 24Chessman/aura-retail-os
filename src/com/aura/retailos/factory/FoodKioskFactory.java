// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.kiosk.BaseKiosk;

public class FoodKioskFactory extends KioskFactory {

    // Creates a ConveyorDispenser abstraction suited for food kiosks
    @Override
    public DispenserAbstraction createDispenser() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the food-safety verification module identifier
    @Override
    public String createVerificationModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the pricing module for perishable food products
    @Override
    public String createPricingModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the PerishableInventoryPolicy identifier for food stock
    @Override
    public String createInventoryPolicy() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the kiosk type label for food kiosks
    @Override
    public String getKioskType() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
