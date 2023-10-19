package com.bloomberg.fxdeals.services;

import com.bloomberg.fxdeals.exceptions.AppException;
import com.bloomberg.fxdeals.models.common.ApiResponse;
import com.bloomberg.fxdeals.models.dto.FxDealRequestDto;

public interface FxDealServices {
    ApiResponse addDeal(FxDealRequestDto fxDealRequestDto) throws AppException;

    ApiResponse getFxDealById(String dealId) throws AppException;

    void checkCurrencyCode(String currencyCode) throws AppException;
}
