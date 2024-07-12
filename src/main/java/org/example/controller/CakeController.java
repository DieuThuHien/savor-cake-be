package org.example.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.CreateCakeRequest;
import org.example.dto.request.UpdateCakeRequest;
import org.example.entity.Cake;
import org.example.service.CakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/cake/v1")
@Slf4j
@CrossOrigin
public class CakeController {
    final CakeService cakeService;

    @GetMapping("/all")
    ResponseEntity<List<Cake>> getAllCake() {
        log.info("getAllCake method running");
        return ResponseEntity.ok(cakeService.getAllCake());
    }
    @GetMapping("")
    ResponseEntity<List<Cake>> getCakeType(@RequestParam("cakeType") String cakeType) {
        log.info("getCakeType method running");
        return ResponseEntity.ok(cakeService.getCakeByType(cakeType));
    }

    @PostMapping("")
    ResponseEntity<Cake> addCake(@Valid @RequestBody CreateCakeRequest createCakeRequest) {
        log.info("addCake method running");
        return ResponseEntity.ok(cakeService.addCake(createCakeRequest));
    }
    @PutMapping("")
    ResponseEntity<Cake> updateCake(@Valid @RequestBody UpdateCakeRequest updateCakeRequest) {
        log.info("updateCake method running");
        return ResponseEntity.ok(cakeService.updateCake(updateCakeRequest));
    }
    @DeleteMapping("")
    ResponseEntity<String> deleteCake(@RequestParam("cakeID") String cakeID) {
        log.info("deleteCake method running");
        return ResponseEntity.ok(cakeService.deleteCake(cakeID));
    }
}
