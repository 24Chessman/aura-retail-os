// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

import com.aura.retailos.core.CommandInvoker;

public class RestockCommand implements Command {

    // Name of the product being restocked
    private String productName;

    // Number of units being added to inventory
    private int quantity;

    // Auto-generated unique transaction ID for this restock operation
    private String transactionId;

    // Outcome of the command execution (e.g., SUCCESS, FAILED)
    private String status;

    // Constructs a RestockCommand and auto-generates a transaction ID
    public RestockCommand(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.transactionId = CommandInvoker.generateTransactionId();
        this.status = "PENDING";
    }

    // Simulates the restock workflow: inventory update and logging
    @Override
    public boolean execute() {
        System.out.println("[COMMAND] RestockCommand executing...");
        System.out.println("[COMMAND] Product : " + productName);
        System.out.println("[COMMAND] Quantity: " + quantity + " units added");
        System.out.println("[COMMAND] Inventory updated successfully");
        System.out.println("[COMMAND] Restock logged: " + transactionId);
        this.status = "SUCCESS";
        return true;
    }

    // Returns a pipe-delimited log entry for this restock transaction
    @Override
    public String getLog() {
        return transactionId + " | RESTOCK | " + productName + " x" + quantity
                + " | " + status;
    }
}
