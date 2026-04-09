// Aura Retail OS | IT620 | Code Crafters
// Pattern: Strategy
package com.aura.retailos.strategy;

public interface DispensingStrategy {

    // Executes the dispensing logic for the specified product; returns true if successful
    boolean dispense(String product);

    // Returns the name of this dispensing strategy
    String getStrategyName();
}
