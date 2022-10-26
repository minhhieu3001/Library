package vn.com.ntqsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ntqsolution.authenticate.Authorized;
import vn.com.ntqsolution.exception.AdminException;
import vn.com.ntqsolution.request.AdminRequest;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.ResponseCreator;
import vn.com.ntqsolution.service.AdminService;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Authorized(role = "ROOT")
    @GetMapping("/admins")
    public ResponseEntity<BaseResponse> getAdmins() {
        try {
            return ResponseCreator.createResponseSuccess(adminService.getAdmins());
        } catch (AdminException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ROOT")
    @GetMapping("/admins/{id}")
    public ResponseEntity<BaseResponse> getAdmin(@PathVariable String id) {
        try {
            return ResponseCreator.createResponseSuccess(adminService.getAdmin(id));
        } catch (AdminException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ROOT")
    @PostMapping("/admins/addAdmin")
    public ResponseEntity<BaseResponse> addAdmin(@RequestBody AdminRequest adminRequest) {
        try {
            return ResponseCreator.createResponseSuccess(adminService.addAdmin(adminRequest));
        } catch (AdminException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ROOT")
    @PutMapping("/admins/editAdmin")
    public ResponseEntity<BaseResponse> editAdmin(@RequestBody AdminRequest adminRequest) {
        try {
            return ResponseCreator.createResponseSuccess(adminService.editAdmin(adminRequest));
        } catch (AdminException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ROOT")
    @DeleteMapping("/admins/deleteAdmin/{id}")
    public ResponseEntity<BaseResponse> deleteAdmin(@PathVariable String id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseCreator.createResponseSuccess("Delete admin has id " + id);
        } catch (AdminException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

}
