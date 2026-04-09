// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment;

public class UPIAdapter implements PaymentProcessor {

    // The name/identifier of the UPI payment system being adapted
    private String upiSystem;

    // Adapts the processPayment call to the UPI system's API
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the name of the UPI payment provider
    @Override
    public String getProviderName() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
