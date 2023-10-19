package com.bloomberg.fxdeals.controllers.post;


import com.bloomberg.fxdeals.exceptions.AppException;
import com.bloomberg.fxdeals.models.common.ApiResponse;
import com.bloomberg.fxdeals.models.dto.FxDealRequestDto;
import com.bloomberg.fxdeals.services.FxDealServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class FxDealsPostController {
    @Autowired
    private FxDealServices fxDealServices;

    /**
     * Add new FX Deal to database
     *
     * @param fxDealRequestDto is a transfer object that consumes the body of the request
     * @return Response Entity that contains ResponseDto and status code
     * @throws AppException
     */
    @PostMapping(path = "addDeal")
    public ResponseEntity<ApiResponse> addNewDeal(@RequestBody @Valid FxDealRequestDto fxDealRequestDto)
            throws AppException {
        return new ResponseEntity<>(fxDealServices.addDeal(fxDealRequestDto), HttpStatus.OK);
    }
}
