package com.example.SmartFarmSystem.service;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.repository.SmartFarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SmartFarmService {

    private final SmartFarmRepository smartFarmRepository;

    @Autowired
    public SmartFarmService(SmartFarmRepository smartFarmRepository) {
        this.smartFarmRepository = smartFarmRepository;
    }

    public SmartFarm saveMemo(SmartFarm smartFarm) {
        return smartFarmRepository.save(smartFarm);
    }

    public SmartFarm getMemoById(Long id) {
        return smartFarmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memo not found with id: " + id));
    }

    public void deleteMemoById(Long id) {
        smartFarmRepository.deleteById(id);
    }

    public SmartFarm getLastSmartFarm() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<SmartFarm> smartFarms = smartFarmRepository.findAll(sort);
        return smartFarms.isEmpty() ? null : smartFarms.get(0);
    }
}