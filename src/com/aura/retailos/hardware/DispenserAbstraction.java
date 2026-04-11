// Aura Retail OS | IT620 | Code Crafters
// Supporting class — stub for Bridge pattern (will be expanded later)
package com.aura.retailos.hardware;

public class DispenserAbstraction {

    // The type of dispenser hardware this abstraction wraps
    private String dispenserType;

    // Constructs a DispenserAbstraction for the given hardware type
    public DispenserAbstraction(String dispenserType) {
        this.dispenserType = dispenserType;
    }

    // Delegates product dispensing to the underlying hardware and confirms success
    public boolean dispense(String product) {
        System.out.println("[DISPENSER] Dispensing " + product + " via " + dispenserType);
        return true;
    }

    // Returns the type of dispenser hardware
    public String getDispenserType() {
        return dispenserType;
    }
}
