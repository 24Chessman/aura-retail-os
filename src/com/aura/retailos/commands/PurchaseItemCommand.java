// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

import com.aura.retailos.core.CommandInvoker;

public class PurchaseItemCommand implements Command {

    // Name of the product being purchased
    private String productName;

    // Number of units being purchased
    private int quantity;

    // Payment method selected by the customer (e.g., UPI, CreditCard, DigitalWallet)
    private String paymentMethod;

    // Auto-generated unique transaction ID for this purchase
    private String transactionId;

    // Outcome of the command execution (e.g., SUCCESS, FAILED)
    private String status;

    // Constructs a PurchaseItemCommand and auto-generates a transaction ID
    public PurchaseItemCommand(String productName, int quantity, String paymentMethod) {
        this.productName = productName;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
        this.transactionId = CommandInvoker.generateTransactionId();
        this.status = "PENDING";
    }

    // Simulates the full purchase workflow: payment, dispensing, inventory update, and logging
    @Override
    public boolean execute() {
        System.out.println("[COMMAND] PurchaseItemCommand executing...");
        System.out.println("[COMMAND] Product    : " + productName + " x" + quantity);
        System.out.println("[COMMAND] Payment    : Processing via " + paymentMethod + "...");
        System.out.println("[COMMAND] Payment    : Approved");
        System.out.println("[COMMAND] Dispense   : Item dispensed successfully");
        System.out.println("[COMMAND] Inventory  : Stock updated");
        System.out.println("[COMMAND] Transaction logged: " + transactionId);
        this.status = "SUCCESS";
        return true;
    }

    // Returns a pipe-delimited log entry for this purchase transaction
    @Override
    public String getLog() {
        return transactionId + " | PURCHASE | " + productName + " x" + quantity
                + " | " + paymentMethod + " | " + status;
    }
}
