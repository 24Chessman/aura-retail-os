// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge
package com.aura.retailos.hardware.bridge;

public class DispenserAbstraction {

    // The hardware implementation this abstraction delegates to
    protected DispenserImplementation implementation;

    // Constructs the abstraction bound to a specific hardware implementation
    public DispenserAbstraction(DispenserImplementation implementation) {
        this.implementation = implementation;
    }

    // Delegates dispensing of a product to the underlying hardware implementation
    public boolean dispense(String product) {
        System.out.println("[BRIDGE] Delegating dispense(" + product + ") to " + implementation.getType());
        return implementation.execute(product);
    }

    // Replaces the underlying hardware implementation at runtime (hot-swap)
    public void setImplementation(DispenserImplementation impl) {
        System.out.println("[BRIDGE] Swapping implementation to " + impl.getType());
        this.implementation = impl;
    }

    // Returns the currently bound hardware implementation
    public DispenserImplementation getImplementation() {
        return implementation;
    }

    // Returns the status of the currently bound hardware implementation
    public String getStatus() {
        return "Dispenser: " + implementation.getType() + " | Ready";
    }
}
