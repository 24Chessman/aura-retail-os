// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge
package com.aura.retailos.hardware.bridge;

public abstract class DispenserImplementation {

    // Executes the hardware-specific dispensing action for the given product
    public abstract boolean execute(String product);

    // Returns a string identifying the type of hardware dispenser
    public abstract String getType();

    // Performs hardware calibration to prepare for dispensing
    public abstract void calibrate();
}
