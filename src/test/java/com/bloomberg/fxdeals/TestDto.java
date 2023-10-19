package com.bloomberg.fxdeals;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TestDto {

    private String id;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private String dealTimeStamp;
    private String dealAmount;
}
