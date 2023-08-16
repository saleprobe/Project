package com.example.SmartFarmSystem.repository;

import com.example.SmartFarmSystem.domain.SmartFarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SmartFarmRepository extends JpaRepository<SmartFarm, Long> {

}