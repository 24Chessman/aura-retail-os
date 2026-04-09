// Aura Retail OS | IT620 | Code Crafters
// Pattern: Bridge
package com.aura.retailos.hardware.bridge;

public class DispenserAbstraction {

    // The hardware implementation this abstraction delegates to
    protected DispenserImplementation implementation;

    // Constructs the abstraction bound to a specific hardware implementation
    public DispenserAbstraction(DispenserImplementation implementation) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Delegates dispensing of a product to the underlying hardware implementation
    public boolean dispense(String product) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Replaces the underlying hardware implementation at runtime (hot-swap)
    public void setImplementation(DispenserImplementation impl) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the status of the currently bound hardware implementation
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
