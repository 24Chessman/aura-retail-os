// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk;

import java.util.List;

public interface Kiosk {

    // Returns the current operational status of the kiosk
    String getStatus();

    // Performs a named operation on the kiosk; returns true if successful
    boolean performOperation(String op);

    // Returns a list of capabilities this kiosk (or its decorators) provides
    List<String> getCapabilities();

    // Returns the unique identifier of this kiosk
    String getKioskId();
}
