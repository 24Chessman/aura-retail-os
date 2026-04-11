// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk;

import java.util.Arrays;
import java.util.List;

public class BaseKiosk implements Kiosk {

    private String kioskId;
    private String kioskType;
    private String dispenserType;
    private String pricingType;
    private String inventoryPolicy;

    // Constructs a BaseKiosk with all required configuration fields
    public BaseKiosk(String kioskId, String kioskType, String dispenserType,
                     String pricingType, String inventoryPolicy) {
        this.kioskId = kioskId;
        this.kioskType = kioskType;
        this.dispenserType = dispenserType;
        this.pricingType = pricingType;
        this.inventoryPolicy = inventoryPolicy;
    }

    // Returns a formatted status string and prints it to the console
    @Override
    public String getStatus() {
        String status = "[KIOSK] ID: " + kioskId
                + " | Type: " + kioskType
                + " | Dispenser: " + dispenserType
                + " | Pricing: " + pricingType
                + " | Policy: " + inventoryPolicy
                + " | Status: ACTIVE";
        System.out.println(status);
        return status;
    }

    // Prints the operation being performed and always confirms success
    @Override
    public boolean performOperation(String op) {
        System.out.println("[KIOSK] Performing: " + op);
        return true;
    }

    // Returns the list of key capabilities configured on this kiosk
    @Override
    public List<String> getCapabilities() {
        return Arrays.asList(dispenserType, pricingType, inventoryPolicy);
    }

    // Returns the unique kiosk ID
    @Override
    public String getKioskId() {
        return kioskId;
    }
}
