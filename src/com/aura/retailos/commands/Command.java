// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

public interface Command {

    // Executes the command action; returns true if the operation succeeded
    boolean execute();

    // Returns a formatted log string summarising this command's outcome
    String getLog();
}
