// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.List;

public class RefrigerationDecorator extends KioskDecorator {

    // Current temperature maintained by the refrigeration module (in Celsius)
    private double temperature;

    // Status of the cooling system (e.g., ONLINE, OFFLINE)
    private String coolingStatus;

    // Constructs a RefrigerationDecorator wrapping the given kiosk
    public RefrigerationDecorator(Kiosk kiosk) {
        super(kiosk);
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns status combined with refrigeration module state
    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the current temperature of the refrigeration unit
    public double getTemperature() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Sets the cooling status (e.g., after a hardware event)
    public void setCoolingStatus(String status) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns capabilities including refrigeration support
    @Override
    public List<String> getCapabilities() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
