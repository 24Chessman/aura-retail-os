// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

import com.aura.retailos.core.CommandInvoker;

public class RefundCommand implements Command {

    // The ID of the original purchase transaction being reversed
    private String originalTransactionId;

    // Auto-generated unique transaction ID for this refund operation
    private String refundTransactionId;

    // Outcome of the command execution (e.g., SUCCESS, FAILED)
    private String status;

    // Constructs a RefundCommand linked to an original transaction and auto-generates a refund ID
    public RefundCommand(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
        this.refundTransactionId = CommandInvoker.generateTransactionId();
        this.status = "PENDING";
    }

    // Simulates the refund workflow: payment reversal, stock restoration, and logging
    @Override
    public boolean execute() {
        System.out.println("[COMMAND] RefundCommand executing...");
        System.out.println("[COMMAND] Reversing transaction: " + originalTransactionId);
        System.out.println("[COMMAND] Payment reversed successfully");
        System.out.println("[COMMAND] Stock restored");
        System.out.println("[COMMAND] Refund logged: " + refundTransactionId);
        this.status = "SUCCESS";
        return true;
    }

    // Returns a pipe-delimited log entry for this refund transaction
    @Override
    public String getLog() {
        return refundTransactionId + " | REFUND | Ref:" + originalTransactionId
                + " | " + status;
    }
}
