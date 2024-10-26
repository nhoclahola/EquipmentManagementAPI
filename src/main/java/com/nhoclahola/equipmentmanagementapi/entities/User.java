package com.nhoclahola.equipmentmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String fullName;
    private Boolean gender;
    private String phoneNumber;
    private LocalDate dob;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
