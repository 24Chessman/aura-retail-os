// Aura Retail OS | IT620 | Code Crafters
// Pattern: Proxy
package com.aura.retailos.inventory;

import com.aura.retailos.inventory.composite.Product;
import com.aura.retailos.core.CentralRegistry;
import java.time.LocalDateTime;

public class InventoryProxy implements InventoryInterface {

    // The real inventory this proxy guards and delegates to
    private RealInventory realInventory;

    // The identity of the user currently interacting with the inventory
    private String currentUser;

    // The central registry used to check the current system mode
    private CentralRegistry registry;

    // Constructor: initialise the real inventory, obtain the registry singleton
    public InventoryProxy(String currentUser) {
        this.currentUser = currentUser;
        this.realInventory = new RealInventory();
        this.registry = CentralRegistry.getInstance();
        System.out.println("[PROXY] InventoryProxy created for user: " + currentUser);
    }

    // Verifies that the current user is authorised to modify the inventory
    private boolean checkAuthorization() {
        System.out.println("[PROXY] Checking authorization for: " + currentUser);
        if (currentUser.equals("ADMIN") || currentUser.equals("SYSTEM")) {
            return true;
        }
        System.out.println("[PROXY] ACCESS DENIED for user: " + currentUser);
        return false;
    }

    // Records an access event for auditing purposes
    private void logAccess(String operation) {
        System.out.println("[PROXY] LOG: " + currentUser + " performed " + operation
                + " at " + LocalDateTime.now().toString());
    }

    // Ensures the system is in a mode that permits the requested write operation
    private boolean checkSystemMode() {
        String mode = registry.getSystemMode();
        if (mode.equals("EMERGENCY")) {
            System.out.println("[PROXY] EMERGENCY MODE - restricted access");
            return false;
        }
        return true;
    }

    // Checks auth, logs, checks mode, then delegates addProduct to real inventory
    @Override
    public void addProduct(Product p) {
        if (!checkAuthorization()) return;
        logAccess("addProduct");
        if (!checkSystemMode()) return;
        realInventory.addProduct(p);
    }

    // Checks auth, logs, checks mode, then delegates removeProduct to real inventory
    @Override
    public void removeProduct(String id) {
        if (!checkAuthorization()) return;
        logAccess("removeProduct");
        if (!checkSystemMode()) return;
        realInventory.removeProduct(id);
    }

    // Checks auth, logs, checks mode, then delegates updateStock to real inventory
    @Override
    public void updateStock(String id, int qty) {
        if (!checkAuthorization()) return;
        logAccess("updateStock");
        if (!checkSystemMode()) return;
        realInventory.updateStock(id, qty);
    }

    // Logs and forwards getStock — no auth check required for reads
    @Override
    public int getStock(String id) {
        logAccess("getStock");
        return realInventory.getStock(id);
    }

    // Passthrough — returns the full Product object from the real inventory
    public Product getProduct(String id) {
        return realInventory.getProduct(id);
    }

    // Passthrough — prints all products and their current stock levels
    public void printInventory() {
        realInventory.printInventory();
    }
}
