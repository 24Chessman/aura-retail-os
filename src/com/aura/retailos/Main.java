// Aura Retail OS | IT620 | Code Crafters
// Simulation entry point
package com.aura.retailos;

import com.aura.retailos.core.CentralRegistry;
import com.aura.retailos.core.KioskInterface;
import com.aura.retailos.factory.EmergencyKioskFactory;
import com.aura.retailos.factory.FoodKioskFactory;
import com.aura.retailos.factory.PharmacyKioskFactory;
import com.aura.retailos.kiosk.BaseKiosk;

public class Main {

    public static void main(String[] args) {

        // ── START BANNER ────────────────────────────────────────────────────
        System.out.println("==========================================");
        System.out.println("   AURA RETAIL OS = SIMULATION START      ");
        System.out.println("==========================================");

        // ── SCENE 1: Registry Setup ─────────────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 1: Registry Setup ----");
        CentralRegistry registry = CentralRegistry.getInstance();
        registry.setSystemMode("NORMAL");

        // ── SCENE 2: Create FoodKiosk via Factory ───────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 2: Creating FoodKiosk ----");
        BaseKiosk foodKiosk = new FoodKioskFactory().createKiosk("KIOSK-F01");
        registry.registerKiosk(foodKiosk.getKioskId());
        KioskInterface foodInterface = new KioskInterface(foodKiosk);

        // ── SCENE 3: Run Diagnostics ─────────────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 3: Diagnostics ----");
        foodInterface.runDiagnostics();

        // ── SCENE 4: Purchase Flow ───────────────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 4: Purchases ----");
        foodInterface.purchaseItem("Water Bottle", 2, "UPI");
        foodInterface.purchaseItem("Sandwich", 1, "CreditCard");

        // ── SCENE 5: Refund Flow ─────────────────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 5: Refund ----");
        foodInterface.refundTransaction("TXN-001");

        // ── SCENE 6: Restock Flow ────────────────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 6: Restock ----");
        foodInterface.restockInventory("Water Bottle", 10);

        // ── SCENE 7: Print FoodKiosk Transaction History ─────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 7: FoodKiosk Transaction History ----");
        foodInterface.printTransactionHistory();

        // ── SCENE 8: Create PharmacyKiosk via Factory ───────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 8: Creating PharmacyKiosk ----");
        BaseKiosk pharmacyKiosk = new PharmacyKioskFactory().createKiosk("KIOSK-P01");
        registry.registerKiosk(pharmacyKiosk.getKioskId());
        KioskInterface pharmacyInterface = new KioskInterface(pharmacyKiosk);
        pharmacyInterface.purchaseItem("Paracetamol", 1, "DigitalWallet");

        // ── SCENE 9: Create EmergencyKiosk ──────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 9: Creating EmergencyKiosk ----");
        BaseKiosk emergencyKiosk = new EmergencyKioskFactory().createKiosk("KIOSK-E01");
        registry.registerKiosk(emergencyKiosk.getKioskId());
        registry.setSystemMode("EMERGENCY");
        KioskInterface emergencyInterface = new KioskInterface(emergencyKiosk);
        emergencyInterface.purchaseItem("First Aid Kit", 5, "UPI");

        // ── SCENE 10: Print Registry Status ─────────────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 10: Registry Status ----");
        registry.printStatus();

        // ── SCENE 11: Print All Transaction Histories ────────────────────────
        System.out.println("\n[SIMULATION] ---- SCENE 11: All Transaction Histories ----");
        System.out.println("[SIMULATION] >> FoodKiosk (KIOSK-F01):");
        foodInterface.printTransactionHistory();
        System.out.println("[SIMULATION] >> PharmacyKiosk (KIOSK-P01):");
        pharmacyInterface.printTransactionHistory();
        System.out.println("[SIMULATION] >> EmergencyKiosk (KIOSK-E01):");
        emergencyInterface.printTransactionHistory();

        // ── END BANNER ────────────────────────────────────────────────────────
        System.out.println("\n==========================================");
        System.out.println("   AURA RETAIL OS = SIMULATION COMPLETE   ");
        System.out.println("==========================================");
    }
}
