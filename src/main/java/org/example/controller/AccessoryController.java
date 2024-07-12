package org.example.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Accessory;
import org.example.service.AccessoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/accessory/v1")
@Slf4j
@CrossOrigin
public class AccessoryController {

    final AccessoryService accessoryService;

    @GetMapping("/all")
    ResponseEntity<List<Accessory>> getAllAccessory() {
        log.info("getAllAccessory method running");
        return ResponseEntity.ok(accessoryService.getAllAccessory());
    }
}
