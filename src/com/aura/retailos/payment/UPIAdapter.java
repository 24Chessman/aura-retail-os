// Aura Retail OS | IT620 | Code Crafters
// Pattern: Adapter
// Owner: Priya | 202512003
package com.aura.retailos.payment;

import com.aura.retailos.payment.providers.UPISystem;

public class UPIAdapter implements PaymentProcessor {

    // The incompatible third-party UPI system this adapter wraps.
    // External code only ever sees PaymentProcessor — never UPISystem.
    private UPISystem upiSystem = new UPISystem();

    // Converts the unified PaymentProcessor call into the UPI system's
    // own initiateUPITransaction(vpa, amount) signature.
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        System.out.println("[ADAPTER] UPIAdapter converting PaymentProcessor call");
        // paymentDetails carries the VPA (Virtual Payment Address)
        return upiSystem.initiateUPITransaction(paymentDetails, amount);
    }

    // Identifies which third-party provider this adapter wraps
    @Override
    public String getProviderName() {
        return "UPISystem";
    }
}
