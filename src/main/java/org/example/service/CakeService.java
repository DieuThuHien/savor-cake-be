package org.example.service;

import org.example.dto.request.CreateCakeRequest;
import org.example.dto.request.UpdateCakeRequest;
import org.example.entity.Cake;

import java.util.List;

public interface CakeService {
    List<Cake> getAllCake();
    Cake addCake(CreateCakeRequest createCakeRequest);
    Cake updateCake(UpdateCakeRequest updateCakeRequest);
    List<Cake> getCakeByType(String type);
    String deleteCake(String cakeID);
}
