package com.raja.store;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
// @Lazy - it will make create the instance when it's needed otherwise it will create it at start.
public class OrderService {
    private final PaymentService paymentService;

    @Autowired //This defines which constructor the spring should use.
    OrderService(@Qualifier("stripe") PaymentService paymentService){
        //Using Qualifier telling spring boot to use this implementation
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
        System.out.println("This will get created after a bean is created");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("This will get created before a bean is destroyed");
    }

     public void placeOrder() {
        paymentService.processPayment(10);
     }
}
