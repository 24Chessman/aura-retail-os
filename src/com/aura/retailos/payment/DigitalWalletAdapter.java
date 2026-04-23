// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
// Owner: Priya | 202512003
package com.aura.retailos.payment;

import com.aura.retailos.payment.providers.DigitalWallet;

public class DigitalWalletAdapter implements PaymentProcessor {

    // The incompatible third-party wallet this adapter wraps.
    // External code only ever sees PaymentProcessor — never DigitalWallet.
    private DigitalWallet wallet = new DigitalWallet();

    // Converts the unified PaymentProcessor call into the wallet's
    // own debitWallet(userId, amount) signature.
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        System.out.println("[ADAPTER] DigitalWalletAdapter converting PaymentProcessor call");
        // paymentDetails carries the wallet user ID
        return wallet.debitWallet(paymentDetails, amount);
    }

    // Identifies which third-party provider this adapter wraps
    @Override
    public String getProviderName() {
        return "DigitalWallet";
    }
}
