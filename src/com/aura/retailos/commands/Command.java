// Aura Retail OS | IT620 | Code Crafters
// Pattern: Command
package com.aura.retailos.commands;

public interface Command {

    // Executes the command action and returns true if it succeeded
    boolean execute();

    // Returns a formatted log string summarising this command's outcome
    String getLog();
}
