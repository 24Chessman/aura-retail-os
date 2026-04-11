// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.DispenserAbstraction;

public class FoodKioskFactory extends KioskFactory {

    // Returns the kiosk type label for food/metro kiosks
    @Override
    public String getKioskType() {
        return "FoodKiosk";
    }

    // Creates a ConveyorDispenser for food item delivery on metro platforms
    @Override
    public DispenserAbstraction createDispenser() {
        System.out.println("[FACTORY] Initialising ConveyorDispenser for FoodKiosk.");
        return new DispenserAbstraction("ConveyorDispenser");
    }

    // Returns the expiry verification module for perishable products
    @Override
    public String createVerificationModule() {
        return "ExpiryVerification";
    }

    // Returns the dynamic pricing strategy that adjusts price by demand and expiry
    @Override
    public String createPricingModule() {
        return "DynamicPricing";
    }

    // Returns the inventory policy for perishable, time-sensitive stock
    @Override
    public String createInventoryPolicy() {
        return "PerishableInventoryPolicy";
    }
}
