// Aura Retail OS | IT620 | Code Crafters
// Supporting class
package com.aura.retailos.inventory;

import com.aura.retailos.commands.Command;
import java.util.List;

public class InventoryPersistence {

    // Path to the file where inventory data is persisted
    private String filePath;

    // Serialises and saves the current inventory state to disk
    public void saveInventory(RealInventory inv) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Loads and deserialises inventory state from disk
    public RealInventory loadInventory() {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Saves the full transaction command history to disk
    public void saveTransactions(List<Command> history) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Loads the persisted transaction command history from disk
    public List<Command> loadTransactions() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
