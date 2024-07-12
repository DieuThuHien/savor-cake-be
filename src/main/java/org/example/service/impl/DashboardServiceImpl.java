package org.example.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.dto.response.ChartResponse;
import org.example.dto.response.TotalDashboardResponse;
import org.example.entity.Billing;
import org.example.repository.BillingRepository;
import org.example.service.DashboardService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashboardServiceImpl implements DashboardService {

    final BillingRepository billingRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public TotalDashboardResponse calculateTotalDashboard() {
        List<Billing> allList = billingRepository.findAll();

        // Filter and collect lists locally
        List<Billing> listDone = allList.stream()
                .filter(billing -> "done".equals(billing.getStatus()))
                .toList();

        List<Billing> listReject = allList.stream()
                .filter(billing -> "reject".equals(billing.getStatus()))
                .toList();

        List<Billing> listProcessing = allList.stream()
                .filter(billing -> "processing".equals(billing.getStatus()))
                .toList();

        // Calculate totals
        BigInteger totalMoney = listDone.stream()
                .map(Billing::getTotalPrice) // Assuming getTotalPrice() returns BigInteger
                .reduce(BigInteger.ZERO, BigInteger::add);

        BigInteger totalBillDoneQuantity = BigInteger.valueOf(listDone.size());
        BigInteger totalBillQuantity = BigInteger.valueOf(allList.size());
        BigInteger totalBillRejectQuantity = BigInteger.valueOf(listReject.size());
        BigInteger totalBillProcessingQuantity = BigInteger.valueOf(listProcessing.size());
        return TotalDashboardResponse.builder()
                .totalMoney(totalMoney)
                .totalBillDoneQuantity(totalBillDoneQuantity)
                .totalBillQuantity(totalBillQuantity)
                .totalBillRejectQuantity(totalBillRejectQuantity)
                .totalBillProcessingQuantity(totalBillProcessingQuantity)
                .build();
    }

    @Override
    public ChartResponse getChartStatic(String by) {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate;
        List<Billing> billings;
        ChartResponse chartResponse = null;
        switch (by) {
            case "1":
                startDate = endDate.minusDays(6).toLocalDate().atStartOfDay();
                billings = findAllByStatusAndCreatedDateBetween("done", startDate, endDate);
                chartResponse = statisticChart(startDate, endDate, billings);
                break;

            case "2":
                startDate = endDate.minusDays(14).toLocalDate().atStartOfDay();
                billings = findAllByStatusAndCreatedDateBetween("done", startDate, endDate);
                chartResponse = statisticChart(startDate, endDate, billings);
                break;
            default:
                startDate = endDate.minusDays(30).toLocalDate().atStartOfDay();
                billings = findAllByStatusAndCreatedDateBetween("done", startDate, endDate);
                chartResponse = statisticChart(startDate, endDate, billings);
                break;

        }

        return chartResponse;
    }



    private List<Billing> findAllByStatusAndCreatedDateBetween(String status, LocalDateTime startDate, LocalDateTime endDate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status)
                .and("createdDate").gte(startDate).lte(endDate));
        query.with(Sort.by(Sort.Order.desc("createdDate"))); // Optional: Sorting by createdDate in descending order
        return mongoTemplate.find(query, Billing.class);
    }

    private ChartResponse statisticChart(LocalDateTime startDate, LocalDateTime endDate, List<Billing> billings) {
        // Initialize map to store daily totals
        Map<LocalDate, BigInteger> dailyTotals = new HashMap<>();

        // Iterate over each day in the range
        LocalDate date = startDate.toLocalDate();
        while (!date.isAfter(endDate.toLocalDate())) {
            BigInteger total = BigInteger.ZERO;
            for (Billing billing : billings) {
                LocalDate billingDate = billing.getCreatedDate().toLocalDate();
                if (billingDate.equals(date)) {
                    total = total.add(billing.getTotalPrice());
                }
            }
            dailyTotals.put(date, total);
            date = date.plusDays(1);
        }
        return ChartResponse.builder().dailyTotals(dailyTotals).build();
    }
}
