// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.ArrayList;
import java.util.List;

public class RefrigerationDecorator extends KioskDecorator {

    // Current temperature maintained by the refrigeration module (in Celsius)
    private double temperature = 4.0;

    // Status of the cooling system (e.g., ACTIVE, OFFLINE)
    private String coolingStatus = "ACTIVE";

    // Constructs a RefrigerationDecorator wrapping the given kiosk
    public RefrigerationDecorator(Kiosk kiosk) {
        super(kiosk);
        System.out.println("[DECORATOR] RefrigerationModule attached to " + kiosk.getKioskId());
    }

    // Returns status combined with refrigeration module state
    @Override
    public String getStatus() {
        return super.getStatus() + " | Temp: " + temperature + "C | Cooling: " + coolingStatus;
    }

    // Returns the current temperature of the refrigeration unit
    public double getTemperature() {
        return temperature;
    }

    // Sets the cooling status and announces the change
    public void setCoolingStatus(String status) {
        this.coolingStatus = status;
        System.out.println("[REFRIGERATION] Status changed to: " + status);
    }

    // Returns whether the refrigeration unit is actively cooling
    public boolean isRefrigerationActive() {
        return coolingStatus.equals("ACTIVE");
    }

    // Returns capabilities including refrigeration support
    @Override
    public List<String> getCapabilities() {
        List<String> caps = new ArrayList<>(super.getCapabilities());
        caps.add("RefrigerationUnit");
        return caps;
    }
}
