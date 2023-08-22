package com.royaltheorem.UserService;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "UserDetails")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false,unique = true)
    long contact;
    @Column(nullable = false)
    String password;
    @Transient
    List<Order> orders;


}
