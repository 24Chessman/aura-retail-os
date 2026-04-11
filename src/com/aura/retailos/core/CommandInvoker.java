// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.core;

import com.aura.retailos.commands.Command;
import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    // Ordered history of all commands that have been executed
    private List<Command> commandHistory = new ArrayList<>();

    // Global counter used to generate sequential transaction IDs (shared by all command classes)
    private static int transactionCounter = 1;

    // Executes the given command and records it in the history
    public void executeCommand(Command cmd) {
        cmd.execute();
        commandHistory.add(cmd);
    }

    // Generates a unique transaction ID in the format TXN-001, TXN-002, etc.
    // Static so command constructors can call it directly without an invoker instance
    public static String generateTransactionId() {
        return "TXN-" + String.format("%03d", transactionCounter++);
    }

    // Returns the full ordered list of executed commands
    public List<Command> getHistory() {
        return commandHistory;
    }

    // Prints a numbered transaction history log with totals
    public void printHistory() {
        System.out.println("[INVOKER] ===== Transaction History =====");
        int i = 1;
        for (Command cmd : commandHistory) {
            System.out.println("[INVOKER] " + i++ + ". " + cmd.getLog());
        }
        System.out.println("[INVOKER] Total transactions: " + commandHistory.size());
    }
}
