package com.example.SmartFarmSystem.domain.entity;

import com.example.SmartFarmSystem.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
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

    @Digits(integer = 10, fraction = 0)
    @JsonProperty("user_sf_id")
    @Column(nullable = true)
    private int user_sf_id;

    private String cropname;
    private short period;
    private String variety;
    private short growthstate;

    private UserRole role;
    public void setUserSfId(int userSfId) {
        this.user_sf_id = userSfId;
    }
}