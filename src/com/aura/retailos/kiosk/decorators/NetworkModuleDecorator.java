// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.List;

public class NetworkModuleDecorator extends KioskDecorator {

    // Whether the kiosk is currently connected to the city network
    private boolean isConnected;

    // Timestamp of the last successful sync with the city monitoring system
    private String lastSyncTime;

    // Constructs a NetworkModuleDecorator wrapping the given kiosk
    public NetworkModuleDecorator(Kiosk kiosk) {
        super(kiosk);
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns status combined with network connectivity state
    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns whether the network module is currently connected
    public boolean isConnected() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the timestamp of the last successful sync
    public String getLastSyncTime() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns capabilities including network connectivity support
    @Override
    public List<String> getCapabilities() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
