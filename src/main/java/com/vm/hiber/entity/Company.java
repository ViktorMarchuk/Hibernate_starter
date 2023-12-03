package com.vm.hiber.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "userList")
@Entity
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<User> userList = new HashSet<>();

    public void addUser(User user){
        userList.add(user);
        user.setCompany(this);

    }
}
