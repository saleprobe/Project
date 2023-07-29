package com.example.SmartFarmSystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "smart_farms")
@Entity
public class SmartFarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    @Digits(integer = 3, fraction = 0)
    @JsonProperty("sf_id")
    @Column(nullable = true)
    private int sf_id;

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
    @Min(value = 0)
    @Max(value = 10)
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