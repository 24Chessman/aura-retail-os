// Aura Retail OS | IT620 | Code Crafters
// Supporting class
package com.aura.retailos.monitoring;

import java.util.List;
import java.util.ArrayList;

public class CityMonitoringSystem {

    // Accumulated list of alert messages received from kiosks
    private List<String> alerts;

    // Receives and stores a raw alert message from any kiosk or subsystem
    public void receiveAlert(String alert) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the full list of received alerts
    public List<String> getAlerts() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Creates and logs a structured failure alert for a specific kiosk
    public void notifyFailure(String kioskId, String reason) {
        throw new UnsupportedOperationException("To be implemented");
    }
}
