// Aura Retail OS | IT620 | Code Crafters
// Pattern: Facade — wires all 10 patterns into a single entry-point
package com.aura.retailos.core;

import com.aura.retailos.commands.PurchaseItemCommand;
import com.aura.retailos.commands.RefundCommand;
import com.aura.retailos.commands.RestockCommand;
import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.inventory.InventoryPersistence;
import com.aura.retailos.inventory.InventoryProxy;
import com.aura.retailos.inventory.composite.Product;
import com.aura.retailos.kiosk.BaseKiosk;
import com.aura.retailos.kiosk.Kiosk;
import com.aura.retailos.payment.PaymentProcessor;

public class KioskInterface {

    // The kiosk this facade manages (can be a decorated Kiosk)
    private Kiosk kiosk;

    // The command invoker used to execute and track all transactions
    private CommandInvoker invoker;

    // Reference to the global central registry
    private CentralRegistry registry;

    // Proxy that guards and controls all inventory access (Proxy pattern)
    private InventoryProxy inventoryProxy;

    // Adapter-backed payment processor for charge operations (Adapter pattern)
    private PaymentProcessor paymentProcessor;

    // Bridge abstraction that delegates product dispensing to hardware (Bridge pattern)
    private DispenserAbstraction dispenser;

    // Legacy constructor — kept for backward compatibility with existing call sites
    public KioskInterface(BaseKiosk kiosk) {
        this.kiosk = kiosk;
        this.invoker = new CommandInvoker();
        this.registry = CentralRegistry.getInstance();
        this.inventoryProxy = new InventoryProxy("SYSTEM");
        this.dispenser = kiosk.getDispenser();
        System.out.println("[FACADE] KioskInterface initialized for: " + kiosk.getKioskId());
    }

    // Full constructor: accepts an explicit PaymentProcessor so the correct
    // Adapter implementation (CreditCard / UPI / DigitalWallet) can be injected
    public KioskInterface(BaseKiosk kiosk, PaymentProcessor payment) {
        this.kiosk = kiosk;
        this.invoker = new CommandInvoker();
        this.registry = CentralRegistry.getInstance();
        this.paymentProcessor = payment;
        this.inventoryProxy = new InventoryProxy("SYSTEM");
        this.dispenser = kiosk.getDispenser();
        System.out.println("[FACADE] KioskInterface fully initialized for: " + kiosk.getKioskId());
        System.out.println("[FACADE] Payment provider: " + paymentProcessor.getProviderName());
    }

    // Allows the UI to update the facade's kiosk reference after decorators are attached
    public void updateKioskReference(Kiosk decoratedKiosk) {
        this.kiosk = decoratedKiosk;
    }

    // 7-step purchase flow integrating Proxy, Adapter, Bridge, Command, and Decorator patterns
    public boolean purchaseItem(String productName, int quantity, String paymentMethod) {
        System.out.println("[FACADE] purchaseItem() called for: " + productName + " x" + quantity);

        // Emergency mode purchase limit check
        if ("EMERGENCY".equals(registry.getSystemMode()) && quantity >= 5) {
            System.out.println("[FACADE] EMERGENCY MODE: Purchase limit exceeded");
            return false;
        }

        // Step 1 — Guard: verify sufficient stock via the Proxy
        int stock = inventoryProxy.getStock(productName);
        if (stock < quantity) {
            System.out.println("[FACADE] Purchase failed: insufficient stock for '" + productName
                    + "' (have " + stock + ", need " + quantity + ")");
            return false;
        }

        // Step 2 — Pricing: simulate a flat Rs.50 per unit for now
        double price = quantity * 50.0;
        System.out.println("[FACADE] Computed price: Rs." + price);

        // Step 3 — Payment: delegate to the injected Adapter; fall back gracefully if none set
        if (paymentProcessor != null) {
            boolean paid = paymentProcessor.processPayment(price, paymentMethod);
            if (!paid) {
                System.out.println("[FACADE] Purchase failed: payment declined by "
                        + paymentProcessor.getProviderName());
                return false;
            }
        } else {
            System.out.println("[FACADE] No payment processor configured — skipping charge");
        }

        // Step 4 — Dispensing: delegate to Bridge abstraction if hardware is wired up
        if (dispenser != null) {
            dispenser.dispense(productName);
        }

        // Step 5 — Inventory update: deduct purchased units via the Proxy
        //           RealInventory.updateStock() takes an absolute value, so compute new level first
        inventoryProxy.updateStock(productName, stock - quantity);

        // Step 6 — Logging: create and execute a PurchaseItemCommand so the transaction
        //           is recorded in the invoker's history (Command pattern)
        invoker.executeCommand(new PurchaseItemCommand(productName, quantity, paymentMethod));

        // Step 7 — Signal success back to the caller
        return true;
    }

    // Hides the complexity of creating and executing a RefundCommand for the given transaction
    public boolean refundTransaction(String transactionId) {
        System.out.println("[FACADE] refundTransaction() called for: " + transactionId);
        invoker.executeCommand(new RefundCommand(transactionId));
        return true;
    }

    // Hides the complexity of creating and executing a RestockCommand
    public boolean restockInventory(String productName, int quantity) {
        System.out.println("[FACADE] restockInventory() called");
        invoker.executeCommand(new RestockCommand(productName, quantity));
        return true;
    }

    // Runs a full system diagnostic: kiosk status + inventory snapshot
    public String runDiagnostics() {
        System.out.println("[FACADE] runDiagnostics() called");
        // Delegates to the (possibly decorated) Kiosk — Decorator pattern layers add their output
        String status = kiosk.getStatus();
        System.out.println("[FACADE] Diagnostics result: " + status);
        // Print current inventory levels via the Proxy (read-only, no auth needed)
        System.out.println("[FACADE] --- Inventory Status ---");
        inventoryProxy.printInventory();
        return status;
    }

    // Persists the current command history to disk using InventoryPersistence
    public void saveState() {
        System.out.println("[FACADE] saveState() called");
        InventoryPersistence persistence = new InventoryPersistence();
        persistence.saveTransactions(invoker.getHistory());
        persistence.saveInventory(inventoryProxy.getRealInventory());
        persistence.saveConfig(kiosk.getCapabilities());
        System.out.println("[FACADE] State saved.");
    }

    // Seeds one or more products into this facade's InventoryProxy so that
    // purchaseItem() stock checks have real data to work against.
    // Accepts composite.Product objects (the same type used by the Proxy layer).
    public void seedInventory(Product... products) {
        for (Product p : products) {
            inventoryProxy.addProduct(p);
        }
    }

    // Delegates transaction history printing to the CommandInvoker
    public void printTransactionHistory() {
        invoker.printHistory();
    }
}
