// Aura Retail OS | IT620 | Code Crafters
// Supporting class
package com.aura.retailos.inventory;

import com.aura.retailos.commands.Command;
import com.aura.retailos.inventory.composite.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InventoryPersistence {

    // Path to the file where inventory data is persisted
    private static final String INVENTORY_FILE = "data/inventory.json";

    // Path to the file where transaction history is persisted
    private static final String TRANSACTIONS_FILE = "data/transactions.json";

    // Ensures the data/ directory exists, creating it if necessary
    private void ensureDataDir() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Serialises and saves the current inventory state to disk as JSON
    public void saveInventory(RealInventory inv) {
        ensureDataDir();

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"inventory\": [\n");

        boolean first = true;
        for (Product p : inv.getAllProducts()) {
            if (!first) sb.append(",\n");
            sb.append("    {");
            sb.append("\"id\":\"").append(p.getItemId()).append("\"");
            sb.append(",\"name\":\"").append(p.getName()).append("\"");
            sb.append(",\"price\":").append(p.getPrice());
            sb.append(",\"stock\":").append(inv.getStock(p.getItemId()));
            sb.append("}");
            first = false;
        }

        sb.append("\n  ]\n");
        sb.append("}");

        try (FileWriter fw = new FileWriter(INVENTORY_FILE)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            System.err.println("[PERSISTENCE] ERROR saving inventory: " + e.getMessage());
            return;
        }

        System.out.println("[PERSISTENCE] Inventory saved to inventory.json");
    }

    // Loads and prints inventory state from disk
    public void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("[PERSISTENCE] ERROR loading inventory: " + e.getMessage());
            return;
        }

        System.out.println("[PERSISTENCE] Inventory loaded from inventory.json");
    }

    // Saves the full transaction command history to disk as JSON
    public void saveTransactions(List<Command> history) {
        ensureDataDir();

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"transactions\": [\n");

        for (int i = 0; i < history.size(); i++) {
            // Escape any double-quotes inside the log string for valid JSON
            String log = history.get(i).getLog().replace("\"", "\\\"");
            sb.append("    \"").append(log).append("\"");
            if (i < history.size() - 1) sb.append(",");
            sb.append("\n");
        }

        sb.append("  ]\n");
        sb.append("}");

        try (FileWriter fw = new FileWriter(TRANSACTIONS_FILE)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            System.err.println("[PERSISTENCE] ERROR saving transactions: " + e.getMessage());
            return;
        }

        System.out.println("[PERSISTENCE] Transactions saved to transactions.json");
    }

    // Loads and prints transaction history from disk
    public void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("[PERSISTENCE] ERROR loading transactions: " + e.getMessage());
            return;
        }

        System.out.println("[PERSISTENCE] Transactions loaded from transactions.json");
    }
}
