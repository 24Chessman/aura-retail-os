// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment;

public class DigitalWalletAdapter implements PaymentProcessor {

    // The name/identifier of the digital wallet service being adapted
    private String walletName;

    // Adapts the processPayment call to the digital wallet's API
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the name of the digital wallet payment provider
    @Override
    public String getProviderName() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
