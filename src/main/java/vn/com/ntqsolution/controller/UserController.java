package vn.com.ntqsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ntqsolution.authenticate.Authorized;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.User;
import vn.com.ntqsolution.request.UserRequest;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.ResponseCreator;
import vn.com.ntqsolution.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Authorized(role = "ADMIN")
    @GetMapping("/users/{pageNumber}/{pageSize}")
    public ResponseEntity<BaseResponse> searchAndSort(@RequestParam(value = "searchBy", required = false) String searchBy,
                                                      @RequestParam(value = "searchValue", required = false) String searchValue,
                                                      @RequestParam(value = "sortBy", required = false) String sortBy,
                                                      @RequestParam(value = "sortValue", required = false) String sortValue,
                                                      @PathVariable int pageNumber, @PathVariable int pageSize) {
        try {
            return ResponseCreator.createResponseSuccess(userService.searchAndSort(searchBy, searchValue, sortBy, sortValue, pageNumber, pageSize));
        } catch (Exception e) {
            return ResponseCreator.createResponseError(ResponseCode.USER_NOT_FOUND, e.getMessage());
        }
    }


    @Authorized(role = "ADMIN")
    @GetMapping("/users/{id}")
    public ResponseEntity<BaseResponse> getUser(@PathVariable String id) {
        try {
            User user = userService.getUser(id);
            if(user == null) {
                return ResponseCreator.createResponseError(ResponseCode.USER_NOT_FOUND, "USER NOT FOUND");
            }
            return ResponseCreator.createResponseSuccess(user);
        } catch (Exception e) {
            return ResponseCreator.createResponseError(ResponseCode.USER_NOT_FOUND, e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PostMapping("/users/addUser")
    public ResponseEntity<BaseResponse> addUser(@RequestBody UserRequest userRequest) {
        try {
            User user = userService.addUser(userRequest);
            return ResponseCreator.createResponseSuccess(user);
        } catch (Exception e) {
            return ResponseCreator.createResponseError(ResponseCode.USER_EXISTED, e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PutMapping("/users/editUser")
    public ResponseEntity<BaseResponse> editUser(@RequestBody UserRequest userRequest) {
        try {
            User user = userService.editUser(userRequest);
            return ResponseCreator.createResponseSuccess(user);
        } catch (Exception e) {
            return ResponseCreator.createResponseError(ResponseCode.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @DeleteMapping("/users/deleteUser/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseCreator.createResponseSuccess("Delete user has id: " + id + " success");
        } catch (Exception e) {
            return ResponseCreator.createResponseError(ResponseCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
}
