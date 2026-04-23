// Aura Retail OS | IT620 | Code Crafters
// Pattern: Facade — simulation entry point exercising all 10 patterns
package com.aura.retailos;

// Core — Facade · Command · Singleton
import com.aura.retailos.core.CentralRegistry;
import com.aura.retailos.core.KioskInterface;

// Abstract Factory
import com.aura.retailos.factory.EmergencyKioskFactory;
import com.aura.retailos.factory.FoodKioskFactory;
import com.aura.retailos.factory.PharmacyKioskFactory;

// Kiosk + Decorator
import com.aura.retailos.kiosk.BaseKiosk;
import com.aura.retailos.kiosk.Kiosk;
import com.aura.retailos.kiosk.decorators.RefrigerationDecorator;
import com.aura.retailos.kiosk.decorators.SolarPowerDecorator;

// Bridge
import com.aura.retailos.hardware.bridge.DispenserAbstraction;
import com.aura.retailos.hardware.impl.ConveyorDispenserImpl;

// Strategy
import com.aura.retailos.strategy.ConveyorDispensingStrategy;

// Adapter / Payment
import com.aura.retailos.payment.CreditCardAdapter;
import com.aura.retailos.payment.DigitalWalletAdapter;
import com.aura.retailos.payment.UPIAdapter;

// Proxy + Composite
import com.aura.retailos.inventory.InventoryProxy;
import com.aura.retailos.inventory.composite.Product;
import com.aura.retailos.inventory.composite.ProductBundle;

public class Main {

    public static void main(String[] args) {

        printBanner(
            "AURA RETAIL OS - FINAL SIMULATION",
            "All 10 Design Patterns Demonstrated"
        );

        scenario1_HardwareModuleAttachment();
        scenario2_NewPaymentProvider();
        scenario3_NestedBundleAvailability();

        // ── Registry Summary (Singleton) ──────────────────────────────────
        System.out.println();
        System.out.println("[SIMULATION] ========== REGISTRY SUMMARY ==========");
        CentralRegistry.getInstance().printStatus();

        printBanner(
            "AURA RETAIL OS - SIMULATION COMPLETE",
            "Patterns demonstrated: 10/10",
            "Scenarios completed:   3/3"
        );
    }

    // =========================================================================
    // SCENARIO 1 — Adding a Hardware Module at Runtime (Decorator)
    //
    // Patterns exercised:
    //   Abstract Factory · Bridge · Strategy · Adapter · Facade ·
    //   Command · Singleton · Proxy · Decorator
    // =========================================================================
    private static void scenario1_HardwareModuleAttachment() {
        System.out.println();
        System.out.println("[SIMULATION] ==========================================");
        System.out.println("[SCENARIO 1] Hardware Module Attachment Demo");
        System.out.println("[SIMULATION] ==========================================");

        // ── Step 1: Abstract Factory creates a FoodKiosk ─────────────────
        FoodKioskFactory foodFactory = new FoodKioskFactory();
        BaseKiosk baseKiosk = foodFactory.createKiosk("KIOSK-F01");
        CentralRegistry.getInstance().registerKiosk("KIOSK-F01");

        // ── Steps 2-3: Bridge — ConveyorDispenserImpl + ConveyorStrategy ─
        ConveyorDispenserImpl conveyorImpl = new ConveyorDispenserImpl(8);
        conveyorImpl.setStrategy(new ConveyorDispensingStrategy());   // Strategy pattern
        DispenserAbstraction bridgeDispenser = new DispenserAbstraction(conveyorImpl);

        // ── Step 4: Adapter — UPIAdapter as payment provider ─────────────
        UPIAdapter upiAdapter = new UPIAdapter();

        // ── Step 5: Facade — wire kiosk (with Bridge dispenser) + UPIAdapter
        // We build a new BaseKiosk with the bridge dispenser so getDispenser() works.
        BaseKiosk kioskWithDispenser = new BaseKiosk(
                "KIOSK-F01", "FoodKiosk", "ConveyorDispenser",
                "DynamicPricing", "PerishableInventoryPolicy",
                bridgeDispenser);
        KioskInterface kioskInterface = new KioskInterface(kioskWithDispenser, upiAdapter);

        // ── Step 6: Proxy — seed inventory via facade ────────────────────
        kioskInterface.seedInventory(
                new Product("P001", "Water Bottle", 20.0, 10, false),
                new Product("P002", "ColdDrink",    30.0,  8,  true),
                new Product("P003", "Sandwich",     35.0,  5, false)
        );

        // ── Step 7: Print kiosk status — no refrigeration module yet ─────
        Kiosk kiosk = kioskWithDispenser;   // start of the decorator chain
        System.out.println();
        System.out.println("[SCENARIO 1] Kiosk status BEFORE hardware modules:");
        kiosk.getStatus();
        System.out.println("[SCENARIO 1] Capabilities: " + kiosk.getCapabilities());

        // ── Step 8: Warn — no refrigeration; cold item purchase blocked ──
        System.out.println();
        boolean hasRefrigeration = kiosk.getCapabilities().contains("RefrigerationUnit");
        if (!hasRefrigeration) {
            System.out.println("[SCENARIO 1] WARNING: Kiosk has no refrigeration capability.");
            System.out.println("[SCENARIO 1] Skipping ColdDrink — attaching RefrigerationDecorator first.");
        }

        // ── Step 9: Decorator — attach RefrigerationDecorator at RUNTIME ─
        kiosk = new RefrigerationDecorator(kiosk);

        // ── Step 10: Decorator — layer SolarPowerDecorator on top ─────────
        kiosk = new SolarPowerDecorator(kiosk);

        // ── Step 11: Print decorated status (Temp + Solar now visible) ───
        System.out.println();
        System.out.println("[SCENARIO 1] Kiosk status AFTER hardware module attachment:");
        kiosk.getStatus();

        // ── Step 12: Capabilities list includes new hardware modules ─────
        System.out.println("[SCENARIO 1] Capabilities: " + kiosk.getCapabilities());

        // ── Step 13: Purchase ColdDrink — refrigeration is now present ───
        System.out.println();
        System.out.println("[SCENARIO 1] Purchasing ColdDrink (refrigeration now available)...");
        boolean coldDrinkResult = kioskInterface.purchaseItem("P002", 1, "UPI");
        System.out.println("[SCENARIO 1] ColdDrink purchase result: " + coldDrinkResult);

        // ── Step 14: Purchase Water Bottle ────────────────────────────────
        System.out.println();
        System.out.println("[SCENARIO 1] Purchasing Water Bottle...");
        boolean waterResult = kioskInterface.purchaseItem("P001", 2, "UPI");
        System.out.println("[SCENARIO 1] Water Bottle purchase result: " + waterResult);

        // ── Step 15: Transaction history ──────────────────────────────────
        System.out.println();
        kioskInterface.printTransactionHistory();

        System.out.println();
        System.out.println("[SCENARIO 1 COMPLETE] Decorator pattern demonstrated.");
    }

    // =========================================================================
    // SCENARIO 2 — Integrating a New Payment Provider (Adapter)
    //
    // Patterns exercised:
    //   Abstract Factory · Adapter · Facade · Command · Singleton · Proxy
    // =========================================================================
    private static void scenario2_NewPaymentProvider() {
        System.out.println();
        System.out.println("[SIMULATION] ==========================================");
        System.out.println("[SCENARIO 2] New Payment Provider Integration Demo");
        System.out.println("[SIMULATION] ==========================================");

        // ── Step 1: Abstract Factory creates a PharmacyKiosk ─────────────
        PharmacyKioskFactory pharmacyFactory = new PharmacyKioskFactory();
        BaseKiosk pharmacyKiosk = pharmacyFactory.createKiosk("KIOSK-P01");
        CentralRegistry.getInstance().registerKiosk("KIOSK-P01");

        // ── Step 2: Demonstrate the two existing Adapter providers ────────
        System.out.println();
        System.out.println("[SCENARIO 2] Existing adapters processing standalone payments:");
        CreditCardAdapter creditCard = new CreditCardAdapter();
        System.out.println("[SCENARIO 2]   Provider: " + creditCard.getProviderName());
        creditCard.processPayment(250.0, "4111-1111-1111-1111");

        UPIAdapter upi = new UPIAdapter();
        System.out.println("[SCENARIO 2]   Provider: " + upi.getProviderName());
        upi.processPayment(180.0, "user@upi");

        // ── Step 3: Announce new provider — no existing code changes needed
        System.out.println();
        System.out.println("[SCENARIO 2] Now plugging in DigitalWalletAdapter as a NEW provider");
        System.out.println("[SCENARIO 2] WITHOUT changing any existing code.");

        // ── Step 4: Adapter — instantiate DigitalWalletAdapter ────────────
        DigitalWalletAdapter walletAdapter = new DigitalWalletAdapter();

        // ── Step 5: Facade — inject DigitalWalletAdapter into KioskInterface
        KioskInterface pharmacyInterface = new KioskInterface(pharmacyKiosk, walletAdapter);

        // ── Step 6: Proxy — seed Paracetamol into pharmacy inventory ──────
        pharmacyInterface.seedInventory(
                new Product("P004", "Paracetamol", 45.0, 20, false)
        );

        // ── Step 7: Purchase Paracetamol via DigitalWallet ────────────────
        System.out.println();
        System.out.println("[SCENARIO 2] Purchasing Paracetamol via DigitalWallet...");
        boolean result = pharmacyInterface.purchaseItem("P004", 1, "DigitalWallet");
        System.out.println("[SCENARIO 2] Paracetamol purchase result: " + result);

        // ── Step 8: Transaction history for PharmacyKiosk ─────────────────
        System.out.println();
        pharmacyInterface.printTransactionHistory();

        System.out.println();
        System.out.println("[SCENARIO 2 COMPLETE] Adapter pattern demonstrated.");
    }

    // =========================================================================
    // SCENARIO 3 — Nested Bundle Availability (Composite + Proxy)
    //
    // Patterns exercised:
    //   Abstract Factory · Composite · Proxy · Singleton · Facade · Command
    // =========================================================================
    private static void scenario3_NestedBundleAvailability() {
        System.out.println();
        System.out.println("[SIMULATION] ==========================================");
        System.out.println("[SCENARIO 3] Nested Bundle Availability Demo");
        System.out.println("[SIMULATION] ==========================================");

        // ── Step 1: Abstract Factory creates an EmergencyKiosk ───────────
        EmergencyKioskFactory emergencyFactory = new EmergencyKioskFactory();
        BaseKiosk emergencyKiosk = emergencyFactory.createKiosk("KIOSK-E01");
        CentralRegistry.getInstance().registerKiosk("KIOSK-E01");

        UPIAdapter upi = new UPIAdapter();
        KioskInterface kioskInterface = new KioskInterface(emergencyKiosk, upi);

        // ── Step 2: Composite — build the nested bundle tree ──────────────
        System.out.println();
        System.out.println("[SCENARIO 3] Building nested EmergencyKit composite bundle...");

        // Leaf products
        Product bandages   = new Product("P010", "Bandages",    10.0,  5, false);
        Product antiseptic = new Product("P011", "Antiseptic",  15.0,  3, false);
        Product gauze      = new Product("P012", "Gauze",        8.0, 10, false);
        Product water      = new Product("P013", "WaterBottle", 20.0,  8, false);
        Product flashlight = new Product("P014", "Flashlight",  50.0,  2, false);

        // Inner bundle: FirstAidKit
        ProductBundle firstAidKit = new ProductBundle("BUN002", "FirstAidKit");
        firstAidKit.addItem(bandages);
        firstAidKit.addItem(antiseptic);
        firstAidKit.addItem(gauze);

        // Root bundle: EmergencyKit
        ProductBundle emergencyKit = new ProductBundle("BUN001", "EmergencyKit");
        emergencyKit.addItem(firstAidKit);
        emergencyKit.addItem(water);
        emergencyKit.addItem(flashlight);

        // ── Step 3: All items in stock → isAvailable() = true ────────────
        System.out.println();
        System.out.println("[SCENARIO 3] EmergencyKit.isAvailable() = " + emergencyKit.isAvailable());

        // ── Step 4: Total price = 10+15+8+20+50 = 103.0 ──────────────────
        System.out.println("[SCENARIO 3] EmergencyKit.getPrice()    = " + emergencyKit.getPrice());

        // ── Step 5: Set Antiseptic stock to 0 ────────────────────────────
        System.out.println();
        System.out.println("[SCENARIO 3] Setting Antiseptic stock to 0...");
        antiseptic.setStock(0);
        System.out.println("[SCENARIO 3] Antiseptic stock set to 0.");

        // ── Step 6: FirstAidKit.isAvailable() → false (child out of stock)
        System.out.println("[SCENARIO 3] FirstAidKit.isAvailable()  = " + firstAidKit.isAvailable());

        // ── Step 7: EmergencyKit.isAvailable() → false (cascade from child)
        System.out.println("[SCENARIO 3] EmergencyKit.isAvailable() = " + emergencyKit.isAvailable());

        // ── Step 8: Confirm cascade ───────────────────────────────────────
        System.out.println("[SCENARIO 3] Availability cascaded up the entire bundle tree.");

        // ── Step 9: Singleton — set system mode to EMERGENCY ─────────────
        System.out.println();
        CentralRegistry.getInstance().setSystemMode("EMERGENCY");

        // ── Step 10: Proxy — write operations blocked in EMERGENCY mode ──
        System.out.println("[SCENARIO 3] Attempting updateStock in EMERGENCY mode (Proxy should block)...");
        InventoryProxy emergencyProxy = new InventoryProxy("SYSTEM");
        emergencyProxy.updateStock("BUN001", 5);   // checkSystemMode() refuses this

        // ── Step 11: Restock Antiseptic (direct object mutation for demo) ─
        System.out.println();
        System.out.println("[SCENARIO 3] Restocking Antiseptic...");
        antiseptic.setStock(3);
        System.out.println("[SCENARIO 3] Antiseptic stock restored to 3.");

        // ── Step 12: Bundle availability cascades back to true ───────────
        System.out.println("[SCENARIO 3] EmergencyKit.isAvailable() = " + emergencyKit.isAvailable());

        // ── Step 13: Singleton — restore system mode to NORMAL ───────────
        System.out.println();
        CentralRegistry.getInstance().setSystemMode("NORMAL");

        // ── Seed facade inventory so purchaseItem() can check real stock ──
        kioskInterface.seedInventory(
                new Product("P010", "Bandages",    10.0,  5, false),
                new Product("P011", "Antiseptic",  15.0,  3, false),
                new Product("P012", "Gauze",        8.0, 10, false),
                new Product("P013", "WaterBottle", 20.0,  8, false),
                new Product("P014", "Flashlight",  50.0,  2, false)
        );

        // ── Step 14: Purchase now-available EmergencyKit (rep: Bandages) ─
        System.out.println();
        System.out.println("[SCENARIO 3] Purchasing EmergencyKit — Bandages (P010) x1...");
        boolean kitResult = kioskInterface.purchaseItem("P010", 1, "UPI");
        System.out.println("[SCENARIO 3] EmergencyKit purchase result: " + kitResult);

        // ── Step 15: Save state — Facade delegates to InventoryPersistence ─
        kioskInterface.saveState();

        System.out.println();
        System.out.println("[SCENARIO 3 COMPLETE] Composite and Proxy demonstrated.");
    }

    // ── Banner helper ─────────────────────────────────────────────────────────
    private static void printBanner(String... lines) {
        System.out.println();
        System.out.println("[SIMULATION] ==========================================");
        for (String line : lines) {
            System.out.println("[SIMULATION] " + line);
        }
        System.out.println("[SIMULATION] ==========================================");
    }
}
