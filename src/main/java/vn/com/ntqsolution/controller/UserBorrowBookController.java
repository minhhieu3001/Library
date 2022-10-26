package vn.com.ntqsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ntqsolution.authenticate.Authorized;
import vn.com.ntqsolution.domain.BorrowedInformation;
import vn.com.ntqsolution.exception.BorrowInformationException;
import vn.com.ntqsolution.request.BorrowRequest;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.ResponseCreator;
import vn.com.ntqsolution.service.BorrowedInformationService;

@RestController
public class UserBorrowBookController {

    @Autowired
    BorrowedInformationService borrowedInformationService;

    @Authorized(role = "ADMIN")
    @GetMapping("/borrowed-information")
    public ResponseEntity<BaseResponse> getBorrowedInformations() {
        try {
            return ResponseCreator.createResponseSuccess(borrowedInformationService.getAll());
        } catch (BorrowInformationException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @GetMapping("/borrowed-information/{id}")
    public ResponseEntity<BaseResponse> getBorrowCard(@PathVariable String id) {
        try {
            return ResponseCreator.createResponseSuccess(borrowedInformationService.getById(id));
        } catch (BorrowInformationException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PostMapping("/borrowed-information/addBorrowCard")
    public ResponseEntity<BaseResponse> addBorrowInformation(@RequestBody BorrowRequest borrowRequest) {
        try {
            BorrowedInformation borrowedInformation = borrowedInformationService.add(borrowRequest);
            return ResponseCreator.createResponseSuccess(borrowedInformation);
        } catch (BorrowInformationException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PutMapping("/borrowed-information/editBorrowCard")
    public ResponseEntity<BaseResponse> editBorrowInformation(@RequestBody BorrowRequest borrowRequest) {

        try {
            BorrowedInformation borrowedInformation = borrowedInformationService.edit(borrowRequest);
            return ResponseCreator.createResponseSuccess(borrowedInformation);
        } catch (BorrowInformationException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @DeleteMapping("/borrowed-information/deleteBorrowCard/{id}")
    public ResponseEntity<BaseResponse> deleteBorrowInformation(@PathVariable String id) {
        try {
            borrowedInformationService.deleteById(id);
            return ResponseCreator.createResponseSuccess("Delete borrow card has id: " + id + " success");
        } catch (BorrowInformationException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }
}