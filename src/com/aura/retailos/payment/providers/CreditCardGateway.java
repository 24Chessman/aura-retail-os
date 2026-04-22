// Aura Retail OS | IT620 | Code Crafters
// Simulated third-party credit card payment API
package com.aura.retailos.payment.providers;

import java.util.Random;

public class CreditCardGateway {

    private static final Random random = new Random();

    // Simulates the third-party credit card API's own method signature.
    // In the real world this would be a vendor SDK call — incompatible
    // with our PaymentProcessor interface until the Adapter wraps it.
    public boolean processCardPayment(String cardNumber, double amount) {

        // Extract the last 4 digits of the card number for display
        String last4 = cardNumber.length() >= 4
                ? cardNumber.substring(cardNumber.length() - 4)
                : cardNumber;

        System.out.println("[GATEWAY] CreditCardGateway processing card ending "
                + last4 + " for amount Rs." + amount);

        // Generate a random 4-digit authorization code
        int authCode = 1000 + random.nextInt(9000);
        System.out.println("[GATEWAY] Authorization code: AUTH-" + authCode);

        return true;
    }
}
