// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment;

public class CreditCardAdapter implements PaymentProcessor {

    // The name/identifier of the credit card gateway being adapted
    private String gatewayName;

    // Adapts the processPayment call to the credit card gateway's API
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        throw new UnsupportedOperationException("To be implemented");
    }

    // Returns the name of the credit card payment provider
    @Override
    public String getProviderName() {
        throw new UnsupportedOperationException("To be implemented");
    }
}
