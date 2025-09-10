package com.raja.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();
    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }
    @Override
    public User findByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}
