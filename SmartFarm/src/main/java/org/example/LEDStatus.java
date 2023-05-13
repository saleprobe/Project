package org.example;

import org.springframework.data.jpa.repository.*;
import javax.persistence.*;
import org.springframework.stereotype.Repository;

@Entity
@Table(name = "led_status")
@Repository
public class LEDStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Boolean status;

    public LEDStatus() {
    }

    public LEDStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}