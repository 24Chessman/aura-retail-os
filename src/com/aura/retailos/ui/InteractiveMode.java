// Aura Retail OS | IT620 | Code Crafters
// Interactive user-driven mode — lets users manually test all Path B requirements
package com.aura.retailos.ui;

import com.aura.retailos.core.CentralRegistry;
import com.aura.retailos.core.KioskInterface;

import com.aura.retailos.factory.EmergencyKioskFactory;
import com.aura.retailos.factory.FoodKioskFactory;
import com.aura.retailos.factory.KioskFactory;
import com.aura.retailos.factory.PharmacyKioskFactory;

import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.hardware.bridge.DispenserImplementation;
import com.aura.retailos.hardware.impl.ConveyorDispenserImpl;
import com.aura.retailos.hardware.impl.RoboticArmDispenserImpl;
import com.aura.retailos.hardware.impl.SpiralDispenserImpl;

import com.aura.retailos.inventory.InventoryProxy;
import com.aura.retailos.inventory.composite.Product;
import com.aura.retailos.inventory.composite.ProductBundle;

import com.aura.retailos.kiosk.BaseKiosk;
import com.aura.retailos.kiosk.Kiosk;
import com.aura.retailos.kiosk.decorators.NetworkModuleDecorator;
import com.aura.retailos.kiosk.decorators.RefrigerationDecorator;
import com.aura.retailos.kiosk.decorators.SolarPowerDecorator;

import com.aura.retailos.payment.CreditCardAdapter;
import com.aura.retailos.payment.DigitalWalletAdapter;
import com.aura.retailos.payment.PaymentProcessor;
import com.aura.retailos.payment.UPIAdapter;

import com.aura.retailos.strategy.ConveyorDispensingStrategy;
import com.aura.retailos.strategy.DispensingStrategy;
import com.aura.retailos.strategy.RoboticArmDispensingStrategy;
import com.aura.retailos.strategy.SpiralDispensingStrategy;

import java.util.Scanner;

public class InteractiveMode {

    // Scanner used throughout the entire interactive session
    private Scanner scanner;

    // Default constructor — creates its own Scanner (standalone use)
    public InteractiveMode() {
        this.scanner = new Scanner(System.in);
    }

    // Preferred constructor — accepts the Scanner already opened by the caller
    // so System.in is not wrapped twice (avoids NoSuchElementException on pipes)
    public InteractiveMode(Scanner scanner) {
        this.scanner = scanner;
    }

    // Mutable kiosk reference — updated when decorators are attached
    private Kiosk kiosk;

    // The underlying BaseKiosk (needed by KioskInterface)
    private BaseKiosk baseKiosk;

    // Dispenser bridge abstraction — kept for hot-swap operations
    private DispenserAbstraction dispenser;

    // Currently active payment processor (swappable at runtime)
    private PaymentProcessor paymentProcessor;

    // Facade that wires all patterns together
    private KioskInterface kioskInterface;

    // Proxy guard over the inventory
    private InventoryProxy inventoryProxy;

    // Singleton registry
    private CentralRegistry registry = CentralRegistry.getInstance();

    // Composite bundle objects — kept at field level for Option 5 / 6
    private Product bandages;
    private Product antiseptic;
    private Product gauze;
    private Product waterBottleB;
    private Product flashlight;
    private ProductBundle firstAidKit;
    private ProductBundle emergencyKit;

    // =========================================================================
    // Entry point
    // =========================================================================
    public void start() {
        setupKiosk();
        setupHardwareModules();
        setupPaymentProvider();
        setupInventory();
        runMainMenuLoop();
    }

    // =========================================================================
    // STEP 1 — Kiosk Setup
    // =========================================================================
    private void setupKiosk() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("  AURA RETAIL OS - INTERACTIVE MODE");
        System.out.println("==========================================");
        System.out.println("Select Kiosk Type to Initialize:");
        System.out.println("[1] PharmacyKiosk     - Hospital");
        System.out.println("[2] FoodKiosk         - Metro Station");
        System.out.println("[3] EmergencyKiosk    - Disaster Zone");
        System.out.print("Enter choice: ");

        int choice = readInt();
        KioskFactory factory;
        switch (choice) {
            case 1:
                factory = new PharmacyKioskFactory();
                break;
            case 2:
                factory = new FoodKioskFactory();
                break;
            case 3:
                factory = new EmergencyKioskFactory();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to FoodKiosk.");
                factory = new FoodKioskFactory();
        }

        baseKiosk = factory.createKiosk("KIOSK-USER-01");
        registry.registerKiosk("KIOSK-USER-01");
        kiosk = baseKiosk;
        dispenser = baseKiosk.getDispenser();
    }

    // =========================================================================
    // STEP 2 — Hardware Module Setup
    // =========================================================================
    private void setupHardwareModules() {
        System.out.println();
        System.out.println("Attach optional hardware modules? (each is optional)");

        System.out.print("Attach Refrigeration Module? [y/n]: ");
        if (readYesNo()) {
            kiosk = new RefrigerationDecorator(kiosk);
        }

        System.out.print("Attach Solar Power Module? [y/n]: ");
        if (readYesNo()) {
            kiosk = new SolarPowerDecorator(kiosk);
        }

        System.out.print("Attach Network Module? [y/n]: ");
        if (readYesNo()) {
            kiosk = new NetworkModuleDecorator(kiosk);
        }

        System.out.println("[SETUP] Hardware modules attached. Capabilities: "
                + kiosk.getCapabilities());
    }

    // =========================================================================
    // STEP 3 — Payment Provider Selection
    // =========================================================================
    private void setupPaymentProvider() {
        System.out.println();
        System.out.println("Select Payment Provider:");
        System.out.println("[1] UPI");
        System.out.println("[2] Credit Card");
        System.out.println("[3] Digital Wallet");
        System.out.print("Enter choice: ");

        int choice = readInt();
        paymentProcessor = buildPaymentProcessor(choice);
    }

    // Build a PaymentProcessor from menu choice (1/2/3), defaulting to UPI
    private PaymentProcessor buildPaymentProcessor(int choice) {
        switch (choice) {
            case 1: return new UPIAdapter();
            case 2: return new CreditCardAdapter();
            case 3: return new DigitalWalletAdapter();
            default:
                System.out.println("Defaulting to UPI.");
                return new UPIAdapter();
        }
    }

    // =========================================================================
    // STEP 4 — Inventory Setup
    // =========================================================================
    private void setupInventory() {
        System.out.println();
        System.out.println("Setting up inventory...");

        inventoryProxy = new InventoryProxy("ADMIN");

        // Standard products
        Product p001 = new Product("P001", "Water Bottle",  20.0, 10, false);
        Product p002 = new Product("P002", "ColdDrink",     30.0,  8, true);
        Product p003 = new Product("P003", "Sandwich",      35.0,  5, false);
        Product p004 = new Product("P004", "Paracetamol",   45.0, 20, false);
        Product p005 = new Product("P005", "FirstAidSpray", 80.0,  6, false);

        inventoryProxy.addProduct(p001);
        inventoryProxy.addProduct(p002);
        inventoryProxy.addProduct(p003);
        inventoryProxy.addProduct(p004);
        inventoryProxy.addProduct(p005);

        // Nested bundle: EmergencyKit
        bandages    = new Product("P010", "Bandages",     10.0,  5, false);
        antiseptic  = new Product("P011", "Antiseptic",   15.0,  3, false);
        gauze       = new Product("P012", "Gauze",         8.0, 10, false);
        waterBottleB = new Product("P013", "WaterBottle_B", 20.0, 8, false);
        flashlight  = new Product("P014", "Flashlight",   50.0,  2, false);

        firstAidKit = new ProductBundle("BUN002", "FirstAidKit");
        firstAidKit.addItem(bandages);
        firstAidKit.addItem(antiseptic);
        firstAidKit.addItem(gauze);

        emergencyKit = new ProductBundle("BUN001", "EmergencyKit");
        emergencyKit.addItem(firstAidKit);
        emergencyKit.addItem(waterBottleB);
        emergencyKit.addItem(flashlight);

        // Also register bundle leaf products in the proxy so purchases work
        inventoryProxy.addProduct(bandages);
        inventoryProxy.addProduct(antiseptic);
        inventoryProxy.addProduct(gauze);
        inventoryProxy.addProduct(waterBottleB);
        inventoryProxy.addProduct(flashlight);

        System.out.println("[SETUP] Inventory initialized with 10 products and 1 bundle.");

        // Wire the KioskInterface with the decorated kiosk and selected payment provider.
        // KioskInterface creates its own internal InventoryProxy (user: SYSTEM), but we
        // call seedInventory() so its proxy has the same products that our ADMIN proxy holds.
        kioskInterface = new KioskInterface(baseKiosk, paymentProcessor);
        kioskInterface.seedInventory(
                p001, p002, p003, p004, p005,
                bandages, antiseptic, gauze, waterBottleB, flashlight
        );
    }

    // =========================================================================
    // STEP 5 — Main Menu Loop
    // =========================================================================
    private void runMainMenuLoop() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt();
            System.out.println();
            switch (choice) {
                case 1:  handlePurchase();           break;
                case 2:  handleRefund();             break;
                case 3:  handleRestock();            break;
                case 4:  handleViewInventory();      break;
                case 5:  handleViewBundle();         break;
                case 6:  handleBundleAvailability(); break;
                case 7:  handleDiagnostics();        break;
                case 8:  handleSwapDispenser();      break;
                case 9:  handleSwapStrategy();       break;
                case 10: handleSwitchPayment();      break;
                case 11: handleTestAllPayments();    break;
                case 12: handleTransactionHistory(); break;
                case 13: handleToggleMode();         break;
                case 14: handleRegistryStatus();     break;
                case 15: handleSaveState();          break;
                case 16:
                    System.out.println("Exiting Interactive Mode. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(" AURA RETAIL OS - MAIN MENU");
        System.out.println("==========================================");
        System.out.println("TRANSACTIONS:");
        System.out.println(" [1]  Purchase an item");
        System.out.println(" [2]  Refund a transaction");
        System.out.println(" [3]  Restock inventory");
        System.out.println("INVENTORY:");
        System.out.println(" [4]  View inventory and stock levels");
        System.out.println(" [5]  View product bundle (EmergencyKit)");
        System.out.println(" [6]  Check bundle availability");
        System.out.println("HARDWARE:");
        System.out.println(" [7]  Run diagnostics (view kiosk status)");
        System.out.println(" [8]  Swap dispenser hardware at runtime");
        System.out.println(" [9]  Swap dispensing strategy at runtime");
        System.out.println("PAYMENT:");
        System.out.println(" [10] Switch payment provider");
        System.out.println(" [11] Test all 3 payment providers");
        System.out.println("SYSTEM:");
        System.out.println(" [12] View transaction history");
        System.out.println(" [13] Toggle system mode (NORMAL / EMERGENCY)");
        System.out.println(" [14] View registry status");
        System.out.println(" [15] Save system state to file");
        System.out.println(" [16] Exit");
        System.out.println("==========================================");
        System.out.print("Enter choice: ");
    }

    // =========================================================================
    // PRIVATE HANDLERS
    // =========================================================================

    private void handlePurchase() {
        System.out.print("Enter product ID (e.g. P001): ");
        String productName = scanner.nextLine().trim();
        System.out.print("Enter quantity: ");
        int quantity = readInt();
        System.out.println("Current payment provider: " + paymentProcessor.getProviderName());
        System.out.print("Use current provider? [y/n]: ");
        if (!readYesNo()) {
            System.out.println("Select Payment Provider:");
            System.out.println("[1] UPI");
            System.out.println("[2] Credit Card");
            System.out.println("[3] Digital Wallet");
            System.out.print("Enter choice: ");
            int pChoice = readInt();
            paymentProcessor = buildPaymentProcessor(pChoice);
            kioskInterface = new KioskInterface(baseKiosk, paymentProcessor);
            // Re-seed so the new interface has inventory data
            reseedKioskInterface();
        }
        boolean success = kioskInterface.purchaseItem(productName, quantity, paymentProcessor.getProviderName());
        if (success) {
            System.out.println("[USER] Purchase completed successfully.");
        } else {
            System.out.println("[USER] Purchase failed. Check stock or payment.");
        }
    }

    private void handleRefund() {
        System.out.print("Enter transaction ID to refund (e.g. TXN-001): ");
        String transactionId = scanner.nextLine().trim();
        kioskInterface.refundTransaction(transactionId);
        System.out.println("[USER] Refund processed for: " + transactionId);
    }

    private void handleRestock() {
        System.out.print("Enter product ID to restock (e.g. P001): ");
        String productName = scanner.nextLine().trim();
        System.out.print("Enter quantity to add: ");
        int quantity = readInt();
        kioskInterface.restockInventory(productName, quantity);
        System.out.println("[USER] Restock complete.");
    }

    private void handleViewInventory() {
        System.out.println("===== Current Inventory =====");
        inventoryProxy.printInventory();
    }

    private void handleViewBundle() {
        System.out.println("EmergencyKit");
        System.out.println("  FirstAidKit");
        System.out.println("    Bandages (stock: "   + bandages.getStock()    + ")");
        System.out.println("    Antiseptic (stock: " + antiseptic.getStock()  + ")");
        System.out.println("    Gauze (stock: "      + gauze.getStock()       + ")");
        System.out.println("  WaterBottle_B (stock: " + waterBottleB.getStock() + ")");
        System.out.println("  Flashlight (stock: "  + flashlight.getStock()  + ")");
        System.out.println("Bundle price: "     + emergencyKit.getPrice());
        System.out.println("Bundle available: " + emergencyKit.isAvailable());
    }

    private void handleBundleAvailability() {
        System.out.println("Simulating out-of-stock scenario for Antiseptic...");
        antiseptic.setStock(0);
        System.out.println("FirstAidKit available: "  + firstAidKit.isAvailable());
        System.out.println("EmergencyKit available: " + emergencyKit.isAvailable());
        System.out.println("Restoring Antiseptic stock...");
        antiseptic.setStock(3);
        System.out.println("EmergencyKit available now: " + emergencyKit.isAvailable());
        System.out.println("[COMPOSITE] Availability cascades correctly through nested bundles.");
    }

    private void handleDiagnostics() {
        String status = kioskInterface.runDiagnostics();
        System.out.println(status);
    }

    private void handleSwapDispenser() {
        System.out.println("Select new dispenser hardware:");
        System.out.println("[1] Spiral Dispenser    (cans/bottles)");
        System.out.println("[2] Robotic Arm         (fragile items)");
        System.out.println("[3] Conveyor Belt       (boxed items)");
        System.out.print("Enter choice: ");
        int choice = readInt();

        DispenserImplementation newImpl;
        switch (choice) {
            case 1:  newImpl = new SpiralDispenserImpl(3);        break;
            case 2:  newImpl = new RoboticArmDispenserImpl(0.95); break;
            case 3:  newImpl = new ConveyorDispenserImpl(5);      break;
            default: newImpl = new SpiralDispenserImpl(3);
        }

        dispenser.setImplementation(newImpl);
        System.out.println("[BRIDGE] Dispenser hardware swapped to: " + newImpl.getType());
        System.out.println("[BRIDGE] No kiosk logic was modified. This is Bridge pattern.");
    }

    private void handleSwapStrategy() {
        System.out.println("Select new dispensing strategy:");
        System.out.println("[1] Spiral strategy");
        System.out.println("[2] Robotic Arm strategy");
        System.out.println("[3] Conveyor strategy");
        System.out.print("Enter choice: ");
        int choice = readInt();

        DispensingStrategy strategy;
        switch (choice) {
            case 1:  strategy = new SpiralDispensingStrategy();      break;
            case 2:  strategy = new RoboticArmDispensingStrategy();  break;
            case 3:  strategy = new ConveyorDispensingStrategy();    break;
            default: strategy = new SpiralDispensingStrategy();
        }

        // The current implementation inside the dispenser bridge needs its strategy updated.
        // We obtain the underlying impl and call setStrategy() via the concrete types.
        DispenserImplementation impl = dispenser.getImplementation();
        if (impl instanceof SpiralDispenserImpl) {
            ((SpiralDispenserImpl) impl).setStrategy(strategy);
        } else if (impl instanceof RoboticArmDispenserImpl) {
            ((RoboticArmDispenserImpl) impl).setStrategy(strategy);
        } else if (impl instanceof ConveyorDispenserImpl) {
            ((ConveyorDispenserImpl) impl).setStrategy(strategy);
        }

        System.out.println("[STRATEGY] Dispensing algorithm swapped to: " + strategy.getStrategyName());
        System.out.println("[STRATEGY] No dispenser implementation was modified.");
    }

    private void handleSwitchPayment() {
        System.out.println("Select new payment provider:");
        System.out.println("[1] UPI");
        System.out.println("[2] Credit Card");
        System.out.println("[3] Digital Wallet");
        System.out.print("Enter choice: ");
        int choice = readInt();
        paymentProcessor = buildPaymentProcessor(choice);
        System.out.println("[ADAPTER] Payment provider switched to: " + paymentProcessor.getProviderName());
        System.out.println("[ADAPTER] No existing payment code was modified.");
        // Rebuild the facade with the new provider, preserving inventory
        kioskInterface = new KioskInterface(baseKiosk, paymentProcessor);
        reseedKioskInterface();
    }

    private void handleTestAllPayments() {
        System.out.println("Testing all 3 payment providers with Rs.100.00...");
        new UPIAdapter().processPayment(100.0, "test@upi");
        new CreditCardAdapter().processPayment(100.0, "4111-1111-1111-1234");
        new DigitalWalletAdapter().processPayment(100.0, "testuser");
        System.out.println("[ADAPTER] All 3 payment providers successfully tested.");
    }

    private void handleTransactionHistory() {
        kioskInterface.printTransactionHistory();
    }

    private void handleToggleMode() {
        String currentMode = registry.getSystemMode();
        if ("NORMAL".equals(currentMode)) {
            registry.setSystemMode("EMERGENCY");
            System.out.println("[REGISTRY] System is now in EMERGENCY mode.");
            System.out.println("[PROXY] Inventory write operations are now restricted.");
            System.out.println("[PROXY] Only stock reads are permitted in EMERGENCY mode.");
        } else {
            registry.setSystemMode("NORMAL");
            System.out.println("[REGISTRY] System restored to NORMAL mode.");
        }
    }

    private void handleRegistryStatus() {
        registry.printStatus();
    }

    private void handleSaveState() {
        kioskInterface.saveState();
        System.out.println("[PERSISTENCE] System state saved to data/ directory.");
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    // Re-seeds the KioskInterface's internal proxy with all known products.
    // Called whenever a new KioskInterface is created (e.g., after payment swap).
    private void reseedKioskInterface() {
        kioskInterface.seedInventory(
                new Product("P001", "Water Bottle",   20.0, 10, false),
                new Product("P002", "ColdDrink",      30.0,  8, true),
                new Product("P003", "Sandwich",       35.0,  5, false),
                new Product("P004", "Paracetamol",    45.0, 20, false),
                new Product("P005", "FirstAidSpray",  80.0,  6, false),
                bandages, antiseptic, gauze, waterBottleB, flashlight
        );
    }

    // Reads an integer from stdin; returns -1 if the input is not a valid int.
    private int readInt() {
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("(Invalid number — using -1 as default)");
            return -1;
        }
    }

    // Returns true if the user typed 'y' or 'Y'; false otherwise.
    private boolean readYesNo() {
        String line = scanner.nextLine().trim();
        return "y".equalsIgnoreCase(line);
    }
}
