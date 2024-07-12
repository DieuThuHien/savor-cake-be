package org.example.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.response.ChartResponse;
import org.example.dto.response.TotalDashboardResponse;
import org.example.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/dashboard/v1")
@Slf4j
@CrossOrigin
public class DashboardController {
    final DashboardService dashboardService;
    @GetMapping("")
    ResponseEntity<TotalDashboardResponse> getTotal() {
        log.info("getTotal dashboard method running");
        return ResponseEntity.ok(dashboardService.calculateTotalDashboard());
    }
    @GetMapping("static")
    ResponseEntity<ChartResponse> getChartStatic(@RequestParam("by") String by) {
        log.info("getChartStatic method running");
        return ResponseEntity.ok(dashboardService.getChartStatic(by));
    }
}
