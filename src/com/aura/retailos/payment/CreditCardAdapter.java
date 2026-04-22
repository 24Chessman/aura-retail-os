// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
// Owner: Priya | 202512003
package com.aura.retailos.payment;

import com.aura.retailos.payment.providers.CreditCardGateway;

public class CreditCardAdapter implements PaymentProcessor {

    // The incompatible third-party gateway this adapter wraps.
    // External code only ever sees PaymentProcessor — never CreditCardGateway.
    private CreditCardGateway gateway = new CreditCardGateway();

    // Converts the unified PaymentProcessor call into the gateway's
    // own processCardPayment(cardNumber, amount) signature.
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        System.out.println("[ADAPTER] CreditCardAdapter converting PaymentProcessor call");
        // paymentDetails carries the full card number string
        return gateway.processCardPayment(paymentDetails, amount);
    }

    // Identifies which third-party provider this adapter wraps
    @Override
    public String getProviderName() {
        return "CreditCardGateway";
    }
}
