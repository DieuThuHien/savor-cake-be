package org.example.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@Builder
public class ChartResponse {
    Map<LocalDate, BigInteger> dailyTotals;
}
