package com.raja.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter // This will generate getter on compile time
@Setter
@Entity // Add this for entity/model class
@AllArgsConstructor // This will generate constructor with different number of arguments
@NoArgsConstructor
// This will generate constructor no arguments, this is needed if we use AllArgsConstructor
@Builder // This is like protobuf builder
/* Example
User.builder().id(123L).build();
 */
@ToString
@Table(name = "users") // Add table name here
public class User {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Since id is auto increment, add this here
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    //This way we can change the filed name here without changing in database
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name =  "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    // Here adding the field name of address foreign key entity.
    // Since in this relationship, Address is the Owner as it has the foreign key
    @Builder.Default
    // This will add builder for this field
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany
    @JoinTable(name = "user_tags",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    // Here adding the join table's details
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
    // Set because no duplicates

    @ManyToMany
    @JoinTable(name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @Builder.Default
    private Set<Product> wishlists = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public  void addTag(String tagName) {
        var tag = new Tag(tagName);
        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(String tagName) {
        var tag = new Tag(tagName);
        tags.remove(tag);
        tag.getUsers().remove(this);
    }

/*
    Need to do this without lombok
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
*/
}
