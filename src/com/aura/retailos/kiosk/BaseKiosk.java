// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk;

import java.util.List;

public class BaseKiosk implements Kiosk {

    // Unique identifier for this kiosk instance
    private String kioskId;

    // Type of kiosk (e.g., PharmacyKiosk, FoodKiosk, EmergencyReliefKiosk)
    private String kioskType;

    // Type of dispenser assigned to this kiosk
    private String dispenserType;

    // Pricing module type assigned to this kiosk
    private String pricingType;

    // Inventory policy name applied to this kiosk
    private String inventoryPolicy;

    // Constructs a BaseKiosk with all required configuration fields
    public BaseKiosk(String kioskId, String kioskType, String dispenserType, String pricingType, String inventoryPolicy) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns a formatted string describing the kiosk's current state
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Executes a named operation and returns whether it succeeded
    public boolean performOperation(String op) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the base list of capabilities for this kiosk type
    public List<String> getCapabilities() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the unique kiosk ID
    public String getKioskId() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
