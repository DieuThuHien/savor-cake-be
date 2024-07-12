package org.example.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder
public class TotalDashboardResponse {
    private BigInteger totalMoney;
    private BigInteger totalBillQuantity;
    private BigInteger totalBillProcessingQuantity;
    private BigInteger totalBillDoneQuantity;
    private BigInteger totalBillRejectQuantity;

}
