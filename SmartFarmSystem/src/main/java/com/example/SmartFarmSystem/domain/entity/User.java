package com.example.SmartFarmSystem.domain.entity;

import com.example.SmartFarmSystem.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

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

    @Column(precision = 3, scale = 2, nullable = true)
    private BigDecimal ndvi;

    private UserRole role;
    public void setUserSfId(int userSfId) {
        this.user_sf_id = userSfId;
    }

    public void setUserCropname(String cropname) {
        this.cropname = cropname;
    }

    public void setUserPeriod(short period) {
        this.period = period;
    }

    public int getUserSfId() {
        return user_sf_id;
    }

    public String getCropName() {
        return cropname;
    }
}