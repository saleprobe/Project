package com.example.SmartFarmSystem.service;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.repository.SmartFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SmartFarmService {

    private final SmartFarmRepository smartFarmRepository;

    @Autowired
    public SmartFarmService(SmartFarmRepository smartFarmRepository) {
        this.smartFarmRepository = smartFarmRepository;
    }

    public SmartFarm join(SmartFarm smartFarm) {
        return smartFarmRepository.save(smartFarm);
    }

    public SmartFarm findBySfId(int userSfId) {
        Optional<SmartFarm> smartFarm = smartFarmRepository.findBysfid(userSfId);
        return smartFarm.orElse(null);
    }

    public SmartFarm findLatestSmartFarmBySfId(Long sf_id) {

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<SmartFarm> smartFarms = smartFarmRepository.findAll(sort);

        for (SmartFarm smartFarm : smartFarms) {
            if (smartFarm.getsf_id() == sf_id) {
                return smartFarm;
            }
        }
        throw new RuntimeException("SmartFarm not found for sf_id: " + sf_id);
    }

    public void deleteSFById(Long id) {
        smartFarmRepository.deleteById(id);
    }

    public SmartFarm getLastSmartFarm() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<SmartFarm> smartFarms = smartFarmRepository.findAll(sort);
        return smartFarms.isEmpty() ? null : smartFarms.get(0);
    }
}