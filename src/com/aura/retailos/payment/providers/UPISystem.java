// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment.providers;

import java.util.Random;

public class UPISystem {

    private static final Random random = new Random();

    // Simulates the third-party UPI API's own method signature.
    // Uses VPA (Virtual Payment Address) as the identifier — again
    // incompatible with our PaymentProcessor interface until adapted.
    public boolean initiateUPITransaction(String vpa, double amount) {

        System.out.println("[UPI] Initiating UPI transfer of Rs." + amount
                + " to VPA: " + vpa);

        // Generate a random 6-digit UPI reference number
        int ref = 100000 + random.nextInt(900000);
        System.out.println("[UPI] UPI Ref No: UPI-" + ref);

        return true;
    }
}
