package org.example.controller;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.request.UpdateBillingStatusRequest;
import org.example.entity.Billing;
import org.example.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/billing/v1")
@Slf4j
@CrossOrigin
public class BillingController {
    final BillingService billingService;
    @PostMapping("")
    ResponseEntity<Billing> addBilling(@RequestBody Billing billing) throws MessagingException {
        log.info("addBilling method running");
        return ResponseEntity.ok(billingService.addBilling(billing));
    }

    @GetMapping("")
    ResponseEntity<List<Billing>> getAllBilling(@RequestParam(value = "status", defaultValue = "") String status) {
        log.info("getAllBilling method running");
        return ResponseEntity.ok(billingService.getAllBilling(status));
    }

    @PutMapping("")
    ResponseEntity<Billing> updateBillingStatus(@RequestBody UpdateBillingStatusRequest updateBillingStatusRequest) {
        log.info("updateBillingStatus method running");
        return ResponseEntity.ok(billingService.updateBilling(updateBillingStatusRequest));
    }
}
