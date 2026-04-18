// Aura Retail OS | IT620 | Code Crafters
// Pattern: Decorator
package com.aura.retailos.kiosk.decorators;

import com.aura.retailos.kiosk.Kiosk;
import java.util.ArrayList;
import java.util.List;

public class NetworkModuleDecorator extends KioskDecorator {

    // Whether the kiosk is currently connected to the city network
    private boolean isConnected = true;

    // Timestamp of the last successful sync with the city monitoring system
    private String lastSyncTime = "2025-01-01 10:00:00";

    // Constructs a NetworkModuleDecorator wrapping the given kiosk
    public NetworkModuleDecorator(Kiosk kiosk) {
        super(kiosk);
        System.out.println("[DECORATOR] NetworkModule attached to " + kiosk.getKioskId());
    }

    // Returns status combined with network connectivity state
    @Override
    public String getStatus() {
        return super.getStatus()
                + " | Network: " + (isConnected ? "CONNECTED" : "OFFLINE")
                + " | Sync: " + lastSyncTime;
    }

    // Returns whether the network module is currently connected
    public boolean isConnected() {
        return isConnected;
    }

    // Returns the timestamp of the last successful sync
    public String getLastSyncTime() {
        return lastSyncTime;
    }

    // Returns capabilities including network connectivity support
    @Override
    public List<String> getCapabilities() {
        List<String> caps = new ArrayList<>(super.getCapabilities());
        caps.add("NetworkConnectivity");
        return caps;
    }
}
