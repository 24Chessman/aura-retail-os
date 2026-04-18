// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.ArrayList;
import java.util.List;

public class SolarPowerDecorator extends KioskDecorator {

    // Current solar energy input in watts
    private double solarInput = 120.5;

    // Current battery reserve level as a percentage
    private double batteryReserve = 85.0;

    // Constructs a SolarPowerDecorator wrapping the given kiosk
    public SolarPowerDecorator(Kiosk kiosk) {
        super(kiosk);
        System.out.println("[DECORATOR] SolarPowerModule attached to " + kiosk.getKioskId());
    }

    // Returns status combined with solar power module readings
    @Override
    public String getStatus() {
        return super.getStatus() + " | Solar: " + solarInput + "W | Battery: " + batteryReserve + "%";
    }

    // Returns the current solar energy input in watts
    public double getSolarInput() {
        return solarInput;
    }

    // Returns the current battery reserve percentage
    public double getBatteryReserve() {
        return batteryReserve;
    }

    // Returns capabilities including solar power support
    @Override
    public List<String> getCapabilities() {
        List<String> caps = new ArrayList<>(super.getCapabilities());
        caps.add("SolarPowerMonitoring");
        return caps;
    }
}
