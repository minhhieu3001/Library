package vn.com.ntqsolution.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.ResponseCreator;

@ControllerAdvice
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = {BookException.class})
    public ResponseEntity<BaseResponse> handleNotFoundException(BookException exception) {
        return ResponseCreator.createResponseError(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<BaseResponse> handleTokenException(TokenException exception) {
        return ResponseCreator.createResponseError(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<BaseResponse> handleAdminException(AdminException exception) {
        return ResponseCreator.createResponseError(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(BorrowInformationException.class)
    public ResponseEntity<BaseResponse> handleBorrowInformationException(BorrowInformationException exception) {
        return ResponseCreator.createResponseError(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<BaseResponse> handleUserException(UserException exception) {
        return ResponseCreator.createResponseError(exception.getCode(), exception.getMessage());
    }
}



