// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.kiosk.BaseKiosk;

public class PharmacyKioskFactory extends KioskFactory {

    // Creates a SpiralDispenser abstraction suited for pharmacy kiosks
    @Override
    public DispenserAbstraction createDispenser() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the prescription verification module identifier
    @Override
    public String createVerificationModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the pricing module for controlled pharmaceutical products
    @Override
    public String createPricingModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the ControlledInventoryPolicy identifier for pharmacy stock
    @Override
    public String createInventoryPolicy() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the kiosk type label for pharmacy kiosks
    @Override
    public String getKioskType() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
