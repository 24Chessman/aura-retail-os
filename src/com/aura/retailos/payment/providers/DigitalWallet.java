// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment.providers;

import java.util.Random;

public class DigitalWallet {

    private static final Random random = new Random();

    // Simulates the third-party wallet API's own method signature.
    // Uses userId + amount — completely different from our PaymentProcessor
    // interface, which is why the Adapter pattern is needed.
    public boolean debitWallet(String userId, double amount) {

        System.out.println("[WALLET] DigitalWallet debiting Rs." + amount
                + " from user " + userId);

        // Generate a random wallet transaction reference
        int ref = 100000 + random.nextInt(900000);
        System.out.println("[WALLET] Wallet balance updated. Transaction ID: WAL-" + ref);

        return true;
    }
}
