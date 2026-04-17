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
        this.wrappedKiosk = kiosk;
    }

    // Delegates getStatus to the wrapped kiosk (subclasses may augment)
    @Override
    public String getStatus() {
        return wrappedKiosk.getStatus();
    }

    // Delegates performOperation to the wrapped kiosk
    @Override
    public boolean performOperation(String op) {
        return wrappedKiosk.performOperation(op);
    }

    // Delegates getCapabilities to the wrapped kiosk (subclasses may augment)
    @Override
    public List<String> getCapabilities() {
        return wrappedKiosk.getCapabilities();
    }

    // Delegates getKioskId to the wrapped kiosk
    @Override
    public String getKioskId() {
        return wrappedKiosk.getKioskId();
    }
}
