// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.DispenserAbstraction;
import com.aura.retailos.kiosk.BaseKiosk;

public abstract class KioskFactory {

    // Returns the kiosk type label used for console output (implemented by each concrete factory)
    public abstract String getKioskType();

    // Creates and returns the appropriate dispenser for this kiosk type
    public abstract DispenserAbstraction createDispenser();

    // Creates and returns the verification module identifier for this kiosk type
    public abstract String createVerificationModule();

    // Creates and returns the pricing module identifier for this kiosk type
    public abstract String createPricingModule();

    // Creates and returns the inventory policy identifier for this kiosk type
    public abstract String createInventoryPolicy();

    // Orchestrates creation of all components and assembles a fully configured BaseKiosk
    public BaseKiosk createKiosk(String kioskId) {
        System.out.println("[FACTORY] Creating " + getKioskType() + " components...");

        DispenserAbstraction dispenser = createDispenser();
        String verification = createVerificationModule();
        String pricing = createPricingModule();
        String policy = createInventoryPolicy();

        System.out.println("[FACTORY] Dispenser      : " + dispenser.getDispenserType());
        System.out.println("[FACTORY] Verification   : " + verification);
        System.out.println("[FACTORY] Pricing        : " + pricing);
        System.out.println("[FACTORY] Inventory Policy: " + policy);

        BaseKiosk kiosk = new BaseKiosk(kioskId, getKioskType(),
                dispenser.getDispenserType(), pricing, policy);

        System.out.println("[FACTORY] Kiosk " + kioskId + " ready.");
        return kiosk;
    }
}
