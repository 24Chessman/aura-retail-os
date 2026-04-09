// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.List;

public class SolarPowerDecorator extends KioskDecorator {

    // Current solar energy input in watts
    private double solarInput;

    // Current battery reserve level as a percentage
    private double batteryReserve;

    // Constructs a SolarPowerDecorator wrapping the given kiosk
    public SolarPowerDecorator(Kiosk kiosk) {
        super(kiosk);
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns status combined with solar power module readings
    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the current solar energy input in watts
    public double getSolarInput() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the current battery reserve percentage
    public double getBatteryReserve() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns capabilities including solar power support
    @Override
    public List<String> getCapabilities() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
