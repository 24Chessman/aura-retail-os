// Aura Retail OS | IT620 | Code Crafters
// Pattern: Facade
package com.aura.retailos.core;

import com.aura.retailos.commands.PurchaseItemCommand;
import com.aura.retailos.commands.RefundCommand;
import com.aura.retailos.commands.RestockCommand;
import com.aura.retailos.kiosk.BaseKiosk;

public class KioskInterface {

    // The base kiosk this facade manages
    private BaseKiosk kiosk;

    // The command invoker used to execute and track all transactions
    private CommandInvoker invoker;

    // Reference to the global central registry
    private CentralRegistry registry;

    // Initialises the facade, wires up the invoker and registry, and announces readiness
    public KioskInterface(BaseKiosk kiosk) {
        this.kiosk = kiosk;
        this.invoker = new CommandInvoker();
        this.registry = CentralRegistry.getInstance();
        System.out.println("[FACADE] KioskInterface initialized for: " + kiosk.getKioskId());
    }

    // Hides the complexity of creating and executing a PurchaseItemCommand
    public boolean purchaseItem(String productName, int quantity, String paymentMethod) {
        System.out.println("[FACADE] purchaseItem() called");
        invoker.executeCommand(new PurchaseItemCommand(productName, quantity, paymentMethod));
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

    // Delegates to the kiosk for a hardware/status diagnostic report
    public String runDiagnostics() {
        System.out.println("[FACADE] runDiagnostics() called");
        String status = kiosk.getStatus();
        System.out.println("[FACADE] Diagnostics result: " + status);
        return status;
    }

    // Delegates transaction history printing to the CommandInvoker
    public void printTransactionHistory() {
        invoker.printHistory();
    }
}
