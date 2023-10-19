package com.bloomberg.fxdeals.controllers.get;


import com.bloomberg.fxdeals.exceptions.AppException;
import com.bloomberg.fxdeals.models.common.ApiResponse;
import com.bloomberg.fxdeals.services.FxDealServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class FxDealsGetController {


    @Autowired
    private FxDealServices fxDealServices;

    /**
     * request FX Deal from database by ID
     *
     * @param dealId is the Deal Id
     * @return Response Entity that contains ResponseDto and status code
     * @throws AppException
     */
    @GetMapping("getDeal/{dealId}")
    public ResponseEntity<ApiResponse> getDealById(@PathVariable("dealId") String dealId)
            throws AppException {
        return new ResponseEntity<>(fxDealServices.getFxDealById(dealId), HttpStatus.OK);

    }
}
