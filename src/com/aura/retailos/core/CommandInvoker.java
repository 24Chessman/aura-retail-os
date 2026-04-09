// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.core;

import com.aura.retailos.commands.Command;
import java.util.List;
import java.util.ArrayList;

public class CommandInvoker {

    // Ordered history of all commands that have been executed
    private List<Command> commandHistory;

    // Counter used to generate sequential transaction IDs
    private static int transactionCounter;

    // Executes a command and records it in the history
    public void executeCommand(Command cmd) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Generates a unique transaction ID in the format TXN-001, TXN-002, etc.
    public String generateTransactionId() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the full list of executed commands
    public List<Command> getHistory() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Prints a formatted log of all executed commands
    public void printHistory() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
