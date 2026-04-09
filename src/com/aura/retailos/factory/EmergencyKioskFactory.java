// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.kiosk.BaseKiosk;

public class EmergencyKioskFactory extends KioskFactory {

    // Creates a RoboticArmDispenser abstraction suited for emergency kiosks
    @Override
    public DispenserAbstraction createDispenser() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the emergency authorization verification module identifier
    @Override
    public String createVerificationModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the pricing module for emergency relief products
    @Override
    public String createPricingModule() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the EmergencyInventoryPolicy identifier for disaster zone stock
    @Override
    public String createInventoryPolicy() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the kiosk type label for emergency relief kiosks
    @Override
    public String getKioskType() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
