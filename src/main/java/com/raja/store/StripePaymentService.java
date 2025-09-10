package com.raja.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stripe")
@Primary
//Telling spring boot this is the primary implementation of PaymentService if the Qualifier is not there
public class StripePaymentService implements PaymentService {
    @Value("${stripe.apiUrl}")
    private String stripeApiUrl;

    @Value("${stripe.enabled}")
    private boolean stripeEnabled;

    @Value("${stripe.timeout:3000}") // Added default value
    private int stripeTimeout;

    @Value("${stripe.supported-currencies}")
    private List<String> stripeSupportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe Api Url: " + stripeApiUrl);
        System.out.println("Stripe Enabled: " + stripeEnabled);
        System.out.println("Stripe Timeout: " + stripeTimeout);
        System.out.println("Stripe Supported currencies: " + stripeSupportedCurrencies);
        System.out.println("Order processed through Stripe Payment");
    }
}
