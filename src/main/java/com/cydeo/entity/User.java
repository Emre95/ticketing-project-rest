package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private boolean enabled;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
