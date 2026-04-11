// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
package com.aura.retailos.payment;

public interface PaymentProcessor {

    // Processes a payment of the given amount using the provided payment details;
    // returns true if the payment was accepted by the provider
    boolean processPayment(double amount, String details);

    // Returns the name of the payment provider this processor adapts
    String getProviderName();
}
