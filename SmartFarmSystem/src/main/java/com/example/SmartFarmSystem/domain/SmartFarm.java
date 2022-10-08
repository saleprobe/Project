package com.example.SmartFarmSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "smart_farms")
@Entity
public class SmartFarm {

    public int getsf_id() {
        return sfid;
    }

    public void setsf_id(int sf_id) {
        this.sfid = sf_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    @Digits(integer = 10, fraction = 0)
    @JsonProperty("sfid")
    @Column(nullable = true, name = "sf_id")
    private int sfid;

    @JsonProperty("temp")
    @Column(precision = 3, scale = 1, nullable = true)
    private BigDecimal temp;

    @JsonProperty("hum")
    @Column(precision = 3, scale = 1, nullable = true)
    private BigDecimal hum;

    @JsonProperty("led_state")
    @Column(nullable = true)
    private short led_state;

    @JsonProperty("led_toggle")
    @Column(nullable = true)
    private short led_toggle;

    @JsonProperty("led_adj")
    @Column(scale = 0, nullable = true)
    @Min(value = 1)
    @Max(value = 5)
    private int led_adj;

    @JsonProperty("water_state")
    @Column(nullable = true)
    private short water_state;

    @JsonProperty("water_toggle")
    @Column(nullable = true)
    private short water_toggle;

    @JsonProperty("fan_state")
    @Column(nullable = true)
    private short fan_state;

    @JsonProperty("fan_toggle")
    @Column(nullable = true)
    private short fan_toggle;

}