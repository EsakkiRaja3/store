package com.raja.store.services;

import com.raja.store.entities.Address;
import com.raja.store.entities.Profile;
import com.raja.store.entities.User;
import com.raja.store.repositories.AddressRepository;
import com.raja.store.repositories.ProfileRepository;
import com.raja.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("UserDbService")
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;

    @Transactional
    // This annotation will keep the entity Persistence till the function execution completes
    public void showEntityState(){
        User user = User.builder()
                .name("Esakki")
                .email("rajatest@test.com")
                .password("1234")
                .build();
        // Without @Transactional: Transient / Detached
        // With @Transactional: Transient / Detached
        if(entityManager.contains(user)){
            System.out.println("Persistence");
        } else {
            System.out.println("Transient / Detached");
        }
        userRepository.save(user);
        // Without @Transactional: Transient / Detached
        // With @Transactional: Persistence
        if(entityManager.contains(user)){
            System.out.println("Persistence");
        } else {
            System.out.println("Transient / Detached");
        }
    }

    @Transactional
    // Without Transactional annotation, the query will throw error, since we are using LAZY
    // there is no persistence context
    public void showRelatedEntities() {
        // By default, the fetching strategy for one-one & many to one is Eager, this means this will
        // always fetch the related objects
        // The fetching strategy for one to many and many to many is Lazy, this means this will fetch the
        // related object only when it is accessed, through a separate sql query.

        // This will run a separate query without related object user
        // since I applied Lazy fetch in profile entity
        var profile = profileRepository.findById(1L).orElseThrow();

        // This will run a separate query
        System.out.println(profile.getUser().getEmail());
    }

    public void persistRelatedEntities() {
        User user = User.builder()
                .name("Mark")
                .email("marktest@test.com")
                .password("1234")
                .build();

        Address address = Address.builder()
                .street("test_street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
        // This will not save address, to save an address, we need to add:
        // addressRepository.save(address);
        // Or we need to add CASCADE.PERSIST in User entity's relationship field
    }

    @Transactional
    public void deleteRelatedEntities() {
        // To delete user, this will delete the related object
        // if CASCADE.REMOVE is mentioned in entity related field
        // userRepository.deleteById(3L);

        // To delete a related address, add orphanRemoval = true in related field and do this:
        var user = userRepository.findById(5L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }
}
