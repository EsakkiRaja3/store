package com.raja.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    // This field is created through model to db approach
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

/*
    // This is not needed because
    // we don't need to show all the users who have a particular product in their wishlist
    @ManyToMany(mappedBy = "wishlists")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();
 */
}
