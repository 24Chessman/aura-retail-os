// Aura Retail OS | IT620 | Code Crafters
// Pattern: Singleton
package com.aura.retailos.core;

import java.util.List;
import java.util.ArrayList;

public class CentralRegistry {

    // The single global instance of this registry
    private static CentralRegistry instance;

    // Current operational mode of the system (e.g., NORMAL, EMERGENCY)
    private String systemMode;

    // List of all registered kiosk IDs
    private List<String> registeredKiosks;

    // List of currently active transaction IDs
    private List<String> activeTransactions;

    // Private constructor to prevent external instantiation
    private CentralRegistry() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the single global instance, creating it if necessary
    public static CentralRegistry getInstance() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the current system mode
    public String getSystemMode() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Sets the operational mode of the system
    public void setSystemMode(String mode) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Registers a kiosk by its ID into the global registry
    public void registerKiosk(String kioskId) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Prints current global status including kiosks and active transactions
    public void printStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
