// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

public class RestockCommand implements Command {

    // Name of the product being restocked
    private String productName;

    // Number of units being added to inventory
    private int quantity;

    // Unique transaction ID for this restock operation
    private String transactionId;

    // Current status of this command (e.g., PENDING, SUCCESS, FAILED)
    private String status;

    // Constructs a RestockCommand with all required restock details
    public RestockCommand(String productName, int quantity, String transactionId) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Executes the restock: adds stock through the inventory proxy
    @Override
    public boolean execute() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns a formatted log entry for this restock command
    @Override
    public String getLog() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
