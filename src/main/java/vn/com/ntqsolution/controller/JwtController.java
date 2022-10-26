package vn.com.ntqsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.request.JwtRequest;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.JwtResponse;
import vn.com.ntqsolution.response.ResponseCreator;
import vn.com.ntqsolution.service.JwtService;

@RestController
public class JwtController {

    @Autowired
    JwtService jwtService;

    @PostMapping({"/authenticate"})
    public ResponseEntity<BaseResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        try {
            JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);
            return ResponseCreator.createResponseSuccess(jwtResponse);
        } catch (Exception ex) {
            return ResponseCreator.createResponseError(ResponseCode.INVALID_CREDENTIALS, ex.getMessage());
        }
    }
}

