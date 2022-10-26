package vn.com.ntqsolution.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.com.ntqsolution.constant.ResponseCode;

public class ResponseCreator {

    public static ResponseEntity<BaseResponse> createResponseSuccess(Object object) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseCode.SUCCESS);
        baseResponse.setData(object);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    public static ResponseEntity<BaseResponse> createResponseError(int responseCode, Object object) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(responseCode);
        baseResponse.setData(object);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
