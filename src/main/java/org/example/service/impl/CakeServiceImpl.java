package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.request.CreateCakeRequest;
import org.example.dto.request.UpdateCakeRequest;
import org.example.entity.Cake;
import org.example.exception.BadRequestException;
import org.example.mapper.CakeMapper;

import org.example.repository.CakeRepository;
import org.example.service.CakeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CakeServiceImpl implements CakeService {
    final CakeRepository cakeRepository;
    final CakeMapper cakeMapper;
    @Override
    public List<Cake> getAllCake() {
        return cakeRepository.findAll();
    }

    @Override
    @Transactional
    public Cake addCake(CreateCakeRequest createCakeRequest) {
        Cake newCake = cakeMapper.mapToCake(createCakeRequest);
        newCake.setCakeCode(String.join("","V",String.valueOf(Instant.now().toEpochMilli())));
        return cakeRepository.save(newCake);
    }

    @Override
    public Cake updateCake(UpdateCakeRequest updateCakeRequest) {
        Cake oldCake = cakeRepository.findById(updateCakeRequest.getCakeID()).
                orElseThrow(() -> new BadRequestException("Cake is not existed"));
        cakeMapper.mapToCake(updateCakeRequest, oldCake);
        return cakeRepository.save(oldCake);
    }

    @Override
    public List<Cake> getCakeByType(String type) {
        return cakeRepository.findCakeByCakeType(type);
    }

    @Override
    @Transactional
    public String deleteCake(String cakeID) {
        Cake oldCake = cakeRepository.findById(cakeID).
                orElseThrow(() -> new BadRequestException("Cake is not existed"));
        cakeRepository.delete(oldCake);
        return "Delete cake successfully";
    }

}
