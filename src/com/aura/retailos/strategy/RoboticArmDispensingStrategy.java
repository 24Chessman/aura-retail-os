// Aura Retail OS | IT620 | Code Crafters
// Pattern: Strategy
package com.aura.retailos.strategy;

public class RoboticArmDispensingStrategy implements DispensingStrategy {

    // Dispenses a product using the robotic arm precision strategy
    @Override
    public boolean dispense(String product) {
        System.out.println("[STRATEGY-ROBOTIC] Robotic arm dispensing: " + product);
        return true;
    }

    // Returns the name of this strategy
    @Override
    public String getStrategyName() {
        return "RoboticArmDispensingStrategy";
    }
}
