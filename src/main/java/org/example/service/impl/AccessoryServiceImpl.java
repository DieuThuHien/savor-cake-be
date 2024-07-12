package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.entity.Accessory;
import org.example.repository.AccessoryRepository;
import org.example.service.AccessoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccessoryServiceImpl implements AccessoryService {
    final AccessoryRepository accessoryRepository;
    @Override
    public List<Accessory> getAllAccessory() {
        return accessoryRepository.findAll();
    }
}
