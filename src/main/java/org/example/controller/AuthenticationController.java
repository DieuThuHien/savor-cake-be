package org.example.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.LoginRequest;
import org.example.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/auth/v1")
@Slf4j
@CrossOrigin
public class AuthenticationController {
    final AuthenticationService authenticationService;
    @PostMapping("login")
    ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("login method running");
        String token = authenticationService.login(loginRequest);
        Map<String, String> res= new HashMap<>();
        res.put("token", token);
        res.put("username", loginRequest.getUsername());
        return ResponseEntity.ok(res);
    }
}
