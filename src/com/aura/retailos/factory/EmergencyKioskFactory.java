// Aura Retail OS | IT620 | Code Crafters
// Pattern: Abstract Factory
package com.aura.retailos.factory;

import com.aura.retailos.hardware.DispenserAbstraction;

public class EmergencyKioskFactory extends KioskFactory {

    // Returns the kiosk type label for emergency relief kiosks
    @Override
    public String getKioskType() {
        return "EmergencyReliefKiosk";
    }

    // Creates a RoboticArmDispenser for precise delivery in disaster zones
    @Override
    public DispenserAbstraction createDispenser() {
        System.out.println("[FACTORY] Initialising RoboticArmDispenser for EmergencyReliefKiosk.");
        return new DispenserAbstraction("RoboticArmDispenser");
    }

    // Returns the emergency verification module for authorised relief disbursement
    @Override
    public String createVerificationModule() {
        return "EmergencyVerification";
    }

    // Returns the emergency pricing strategy (subsidised/zero-cost distribution)
    @Override
    public String createPricingModule() {
        return "EmergencyPricing";
    }

    // Returns the inventory policy for prioritised emergency stock management
    @Override
    public String createInventoryPolicy() {
        return "EmergencyInventoryPolicy";
    }
}
