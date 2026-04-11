// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.DispenserAbstraction;

public class PharmacyKioskFactory extends KioskFactory {

    // Returns the kiosk type label for pharmacy kiosks
    @Override
    public String getKioskType() {
        return "PharmacyKiosk";
    }

    // Creates a PrescriptionDispenser for controlled medication dispensing
    @Override
    public DispenserAbstraction createDispenser() {
        System.out.println("[FACTORY] Initialising PrescriptionDispenser for PharmacyKiosk.");
        return new DispenserAbstraction("PrescriptionDispenser");
    }

    // Returns the verification module used to validate prescriptions
    @Override
    public String createVerificationModule() {
        return "PrescriptionVerification";
    }

    // Returns the standard price calculation strategy for pharmacy products
    @Override
    public String createPricingModule() {
        return "StandardPricing";
    }

    // Returns the inventory policy that enforces controlled substance limits
    @Override
    public String createInventoryPolicy() {
        return "ControlledInventoryPolicy";
    }
}
