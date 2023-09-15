package com.example.SmartFarmSystem.domain.entity;

import com.example.SmartFarmSystem.domain.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;
    private String password;
    private String nickname;
    private int sf_id;
    private String cropname;
    private short period;
    private String variety;
    private short growthstate;

    private UserRole role;
}