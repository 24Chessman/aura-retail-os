// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge + Strategy
package com.aura.retailos.hardware.impl;

import com.aura.retailos.hardware.bridge.DispenserImplementation;
import com.aura.retailos.strategy.DispensingStrategy;

public class SpiralDispenserImpl extends DispenserImplementation {

    // Speed of the spiral mechanism in rotations per minute
    private int spiralSpeed = 3;

    // Optional strategy override for dispensing behaviour
    private DispensingStrategy strategy;

    // Constructs a SpiralDispenserImpl with a default or configured spiral speed
    public SpiralDispenserImpl(int spiralSpeed) {
        this.spiralSpeed = spiralSpeed;
    }

    // Sets the dispensing strategy and announces the change
    public void setStrategy(DispensingStrategy s) {
        System.out.println("[DISPENSER] Strategy set to: " + s.getStrategyName());
        this.strategy = s;
    }

    // Executes the spiral dispensing mechanism for the given product
    @Override
    public boolean execute(String product) {
        if (strategy != null) {
            return strategy.dispense(product);
        }
        System.out.println("[SPIRAL] Dispensing " + product + " via spiral mechanism at speed " + spiralSpeed);
        return true;
    }

    // Returns the hardware type identifier for this dispenser
    @Override
    public String getType() {
        return "SpiralDispenser";
    }

    // Calibrates the spiral motor speed and alignment
    @Override
    public void calibrate() {
        System.out.println("[SPIRAL] Calibrating spiral at speed " + spiralSpeed);
    }
}
