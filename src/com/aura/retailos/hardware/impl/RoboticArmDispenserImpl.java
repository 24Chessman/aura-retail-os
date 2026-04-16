// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge + Strategy
package com.aura.retailos.hardware.impl;

import com.aura.retailos.hardware.bridge.DispenserImplementation;
import com.aura.retailos.strategy.DispensingStrategy;

public class RoboticArmDispenserImpl extends DispenserImplementation {

    // Precision level of the robotic arm (higher = more accurate placement)
    private double armPrecision = 0.95;

    // Optional strategy override for dispensing behaviour
    private DispensingStrategy strategy;

    // Constructs a RoboticArmDispenserImpl with a given arm precision value
    public RoboticArmDispenserImpl(double armPrecision) {
        this.armPrecision = armPrecision;
    }

    // Sets the dispensing strategy and announces the change
    public void setStrategy(DispensingStrategy s) {
        System.out.println("[DISPENSER] Strategy set to: " + s.getStrategyName());
        this.strategy = s;
    }

    // Executes the robotic arm dispensing action to pick and place the product
    @Override
    public boolean execute(String product) {
        if (strategy != null) {
            return strategy.dispense(product);
        }
        System.out.println("[ROBOTIC-ARM] Dispensing " + product + " with precision " + armPrecision);
        return true;
    }

    // Returns the hardware type identifier for this dispenser
    @Override
    public String getType() {
        return "RoboticArmDispenser";
    }

    // Calibrates the arm joints, sensors, and grip pressure
    @Override
    public void calibrate() {
        System.out.println("[ROBOTIC-ARM] Calibrating arm precision to " + armPrecision);
    }
}
