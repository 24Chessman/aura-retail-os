// Aura Retail OS | IT620 | Code Crafters
// Pattern: Strategy
package com.aura.retailos.strategy;

public class ConveyorDispensingStrategy implements DispensingStrategy {

    // Dispenses a product using the conveyor belt movement strategy
    @Override
    public boolean dispense(String product) {
        System.out.println("[STRATEGY-CONVEYOR] Conveyor belt dispensing: " + product);
        return true;
    }

    // Returns the name of this strategy
    @Override
    public String getStrategyName() {
        return "ConveyorDispensingStrategy";
    }
}
