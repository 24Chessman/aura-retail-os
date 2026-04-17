// Aura Retail OS | IT620 | Code Crafters
// Pattern: Strate
package com.aura.retailos.strategy;

public class SpiralDispensingStrategy implements DispensingStrategy {

    // Dispenses a product using the spiral mechanism strategy
    @Override
    public boolean dispense(String product) {
        System.out.println("[STRATEGY-SPIRAL] Spiral dispensing: " + product);
        return true;
    }

    // Returns the name of this strategy
    @Override
    public String getStrategyName() {
        return "SpiralDispensingStrategy";
    }
}
