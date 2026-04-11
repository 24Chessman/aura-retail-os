// Aura Retail OS | IT620 | Code Crafters
// Pattern: Singleton
package com.aura.retailos.core;

import java.util.ArrayList;
import java.util.List;

public class CentralRegistry {

    // The single global instance — volatile for thread-safe lazy initialisation
    private static volatile CentralRegistry instance;

    // Operational mode of the entire system (e.g., NORMAL, EMERGENCY)
    private String systemMode;

    // List of all kiosk IDs that have registered with the registry
    private List<String> registeredKiosks;

    // Private constructor prevents external instantiation
    private CentralRegistry() {
        this.systemMode = "NORMAL";
        this.registeredKiosks = new ArrayList<>();
    }

    // Returns the single global instance, creating it on first call (double-checked locking)
    public static CentralRegistry getInstance() {
        if (instance == null) {
            synchronized (CentralRegistry.class) {
                if (instance == null) {
                    instance = new CentralRegistry();
                    System.out.println("[REGISTRY] CentralRegistry instance created.");
                }
            }
        }
        return instance;
    }

    // Registers a kiosk by its ID and confirms registration to the console
    public void registerKiosk(String kioskId) {
        registeredKiosks.add(kioskId);
        System.out.println("[REGISTRY] Kiosk registered: " + kioskId);
    }

    // Returns the current operational mode of the system
    public String getSystemMode() {
        return systemMode;
    }

    // Updates the system mode and announces the change to the console
    public void setSystemMode(String mode) {
        this.systemMode = mode;
        System.out.println("[REGISTRY] System mode changed to: " + mode);
    }

    // Prints the full registry status including all kiosks and current mode
    public void printStatus() {
        System.out.println("[REGISTRY] ===== Registry Status =====");
        System.out.println("[REGISTRY] System Mode : " + systemMode);
        System.out.println("[REGISTRY] Registered Kiosks (" + registeredKiosks.size() + "):");
        for (String id : registeredKiosks) {
            System.out.println("[REGISTRY]   -> " + id);
        }
        System.out.println("[REGISTRY] ============================");
    }
}
