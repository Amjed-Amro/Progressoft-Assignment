package com.bloomberg.fxdeals.services.imp;

import com.bloomberg.fxdeals.constants.ApiConstants;
import com.bloomberg.fxdeals.entities.FxDeal;
import com.bloomberg.fxdeals.exceptions.AppException;
import com.bloomberg.fxdeals.models.common.ApiResponse;
import com.bloomberg.fxdeals.models.dto.FxDealRequestDto;
import com.bloomberg.fxdeals.repository.FxDealsRepository;
import com.bloomberg.fxdeals.services.FxDealServices;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class FxDealServicesImp implements FxDealServices {
    @Autowired
    private FxDealsRepository fxDealsRepository;

    /**
     * this method is used to save FX Deal directly to database
     *
     * @param fxDealRequestDto is the object that contains the request payload
     * @return ResponseDto that contains status code "000" for success and success message and the FX Deal Added
     * @throws AppException
     */
    @Override
    public ApiResponse addDeal(FxDealRequestDto fxDealRequestDto) throws AppException {
        checkCurrencyCode(fxDealRequestDto.getFromCurrencyIsoCode());
        checkCurrencyCode(fxDealRequestDto.getToCurrencyIsoCode());
        fxDealsRepository.save(FxDeal.builder()
                .dealId(fxDealRequestDto.getId())
                .dealAmount(fxDealRequestDto.getDealAmount())
                .dealTimeStamp(fxDealRequestDto.getDealTimeStamp())
                .fromCurrencyIsoCode(fxDealRequestDto.getFromCurrencyIsoCode())
                .toCurrencyIsoCode(fxDealRequestDto.getFromCurrencyIsoCode())
                .creationDate(LocalDateTime.now())
                .createdBy("admin")
                .build());
        log.info("new deal was saved to data base");
        return new ApiResponse(new ApiResponse.Response(ApiConstants.RESPONSE.CODE.SUCCESS, ApiConstants.RESPONSE.MESSAGE.REQUEST_SUCCESS, ApiConstants.RESPONSE.STATUS.SUCCESS), fxDealRequestDto);
    }

    /**
     * this method is used to get specific fx deal from database by deal dealId
     *
     * @param dealId is the deal dealId
     * @return ResponseDto that contains status code "000" for success and success message and the FX Deal dto
     * @throws AppException
     */
    @Override
    public ApiResponse getFxDealById(String dealId) throws AppException {

        FxDeal fxDeal = fxDealsRepository.getFxDealByDealId(Integer.valueOf(dealId)).orElseThrow(() -> new AppException(ApiConstants.RESPONSE.CODE.VALIDATION
                , ApiConstants.RESPONSE.MESSAGE.FX_DEAL_NOT_FOUND, ApiConstants.RESPONSE.STATUS.REJECTED));
        FxDealRequestDto dto = new FxDealRequestDto(fxDeal.getDealId(), fxDeal.getFromCurrencyIsoCode(),
                fxDeal.getToCurrencyIsoCode(), fxDeal.getDealTimeStamp(), fxDeal.getDealAmount());
        log.info("deal found");
        return new ApiResponse(new ApiResponse.Response(ApiConstants.RESPONSE.CODE.SUCCESS, ApiConstants.RESPONSE.MESSAGE.REQUEST_SUCCESS, ApiConstants.RESPONSE.STATUS.SUCCESS), dto);

    }

    /**
     * this method is used to validate the currency ISO code
     *
     * @param currencyCode is code to be validated
     * @throws AppException throws exception if the code is not a Currency ISO Code
     */
    @Override
    public void checkCurrencyCode(String currencyCode) throws AppException {
        if (CurrencyCode.getByCode(currencyCode) == null) {
            log.error(currencyCode + " not a currency iso code");
            throw new AppException(ApiConstants.RESPONSE.CODE.FAILED, currencyCode + " not a currency iso code", ApiConstants.RESPONSE.STATUS.FAILED);
        }
    }
}
