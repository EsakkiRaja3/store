package com.raja.store;

import com.raja.store.entities.Address;
import com.raja.store.entities.Profile;
import com.raja.store.entities.Tag;
import com.raja.store.entities.User;
import com.raja.store.repositories.UserRepository;
import com.raja.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
/*
        // ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        // ConfigurableApplicationContext is used to perform things like pre destroy.
        OrderService orderService = context.getBean(OrderService.class);
        // OrderService orderService2 = context.getBean(OrderService.class);
        // This will create only one instance even though it was initialized twice because it is singleton
        orderService.placeOrder();
        NotificationManager notificationManager = context.getBean(NotificationManager.class);
        notificationManager.sendNotification();
        UserService userService = context.getBean(UserService.class);
        userService.registerUser(new com.raja.store.User(123L, "testemail@email.com", "12345", "Raja"));
        // userService.registerUser(new User(123L, "testemail@email.com", "12345", "Raja"));
        context.close();
        // We need to close it if we use ConfigurableApplicationContext
*/
/*
        User user = User.builder().id(123L).name("Raja").build();
        Address address = Address.builder().street("123, street").city("city").build();
        Profile profile = Profile.builder().bio("Raja's bio").build();

        user.addAddress(address);
        user.addTag("Tag1");
        user.setProfile(profile);
        profile.setUser(user);
        System.out.println(user);
 */
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        // Adding interface here because the class is getting created in runtime
//        var repository = context.getBean(UserRepository.class);
//        User user = User.builder()
//                .name("Raja")
//                .email("test@test.com")
//                .password("1234")
//                .build();
//        // This will insert the above date inside the database
//        repository.save(user);
//        var user = repository.findById(1L).orElse(null);
//        System.out.println(user.getEmail());
        UserService userService = context.getBean(UserService.class);
//        userService.showRelatedEntities();
//        userService.persistRelatedEntities();
        userService.deleteRelatedEntities();


    }

}
