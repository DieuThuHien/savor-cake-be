package org.example.service;

import org.example.dto.response.ChartResponse;
import org.example.dto.response.TotalDashboardResponse;

public interface DashboardService {
    TotalDashboardResponse calculateTotalDashboard();
    ChartResponse getChartStatic(String by);
}
