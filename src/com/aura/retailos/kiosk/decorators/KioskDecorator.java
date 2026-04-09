// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.List;

public abstract class KioskDecorator implements Kiosk {

    // The kiosk instance being wrapped by this decorator
    protected Kiosk wrappedKiosk;

    // Constructs a decorator wrapping the given kiosk
    public KioskDecorator(Kiosk kiosk) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Delegates getStatus to the wrapped kiosk (subclasses may augment)
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Delegates performOperation to the wrapped kiosk
    public boolean performOperation(String op) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Delegates getCapabilities to the wrapped kiosk (subclasses may augment)
    public List<String> getCapabilities() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Delegates getKioskId to the wrapped kiosk
    public String getKioskId() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
