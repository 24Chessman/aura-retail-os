// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge + Strategy
package com.aura.retailos.hardware.impl;

import com.aura.retailos.hardware.bridge.DispenserImplementation;
import com.aura.retailos.strategy.DispensingStrategy;

public class ConveyorDispenserImpl extends DispenserImplementation {

    // Speed of the conveyor belt in units per minute
    private int beltSpeed = 5;

    // Optional strategy override for dispensing behaviour
    private DispensingStrategy strategy;

    // Constructs a ConveyorDispenserImpl with a configured belt speed
    public ConveyorDispenserImpl(int beltSpeed) {
        this.beltSpeed = beltSpeed;
    }

    // Sets the dispensing strategy and announces the change
    public void setStrategy(DispensingStrategy s) {
        System.out.println("[DISPENSER] Strategy set to: " + s.getStrategyName());
        this.strategy = s;
    }

    // Executes the conveyor belt dispensing action to deliver the product
    @Override
    public boolean execute(String product) {
        if (strategy != null) {
            return strategy.dispense(product);
        }
        System.out.println("[CONVEYOR] Dispensing " + product + " via belt at speed " + beltSpeed);
        return true;
    }

    // Returns the hardware type identifier for this dispenser
    @Override
    public String getType() {
        return "ConveyorDispenser";
    }

    // Calibrates belt tension, speed controller, and sensors
    @Override
    public void calibrate() {
        System.out.println("[CONVEYOR] Calibrating belt speed to " + beltSpeed);
    }
}
