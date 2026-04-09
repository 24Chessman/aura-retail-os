// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.kiosk.BaseKiosk;

public abstract class KioskFactory {

    // Creates and returns the appropriate dispenser abstraction for this kiosk type
    public abstract DispenserAbstraction createDispenser();

    // Creates and returns the name/type of the verification module for this kiosk type
    public abstract String createVerificationModule();

    // Creates and returns the name/type of the pricing module for this kiosk type
    public abstract String createPricingModule();

    // Creates and returns the inventory policy identifier for this kiosk type
    public abstract String createInventoryPolicy();

    // Returns the kiosk type label (e.g., "PharmacyKiosk")
    public abstract String getKioskType();

    // Creates a fully assembled BaseKiosk using this factory's configuration
    public BaseKiosk createKiosk(String kioskId) {
        throw new UnsupportedOperationException("To be implemented");
    }
}
