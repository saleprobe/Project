package com.example.SmartFarmSystem.repository;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SmartFarmRepository extends JpaRepository<SmartFarm, Long> {
    Optional<SmartFarm> findBysfid(int sfid);

//    Optional<SmartFarm> findBySf_Id(int sf_id);


}