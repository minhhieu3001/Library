package vn.com.ntqsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.ntqsolution.authenticate.Authorized;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.Book;
import vn.com.ntqsolution.exception.BookException;
import vn.com.ntqsolution.request.BookRequest;
import vn.com.ntqsolution.response.BaseResponse;
import vn.com.ntqsolution.response.ResponseCreator;
import vn.com.ntqsolution.service.BookService;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Authorized(role = "ADMIN")
    @GetMapping("/books/{pageNumber}/{pageSize}")
    public ResponseEntity<BaseResponse> search(@RequestParam(value = "searchBy", required = false) String searchBy,
                                               @RequestParam(value = "searchValue", required = false) String searchValue,
                                               @RequestParam(value = "sortBy", required = false) String sortBy,
                                               @RequestParam(value = "sortValue", required = false) String sortValue,
                                               @PathVariable int pageNumber, @PathVariable int pageSize) {
        try {
            return ResponseCreator.createResponseSuccess(bookService.searchAndSort(searchBy, searchValue, sortBy, sortValue, pageNumber, pageSize));
        } catch (BookException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @GetMapping("/books/{id}")
    public ResponseEntity<BaseResponse> getBook(@PathVariable String id) {
        try {
            Book book = bookService.getBook(id);
            if(book == null) {
                return ResponseCreator.createResponseError(ResponseCode.BOOK_NOT_FOUND, "BOOK NOT FOUND");
            }
            return ResponseCreator.createResponseSuccess(book);
        } catch (BookException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PostMapping("/books/addBook")
    public ResponseEntity<BaseResponse> addBook(@RequestBody BookRequest bookRequest) {
        try {
            Book book = bookService.addBook(bookRequest);
            return ResponseCreator.createResponseSuccess(book);
        } catch (BookException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @PutMapping("/books/editBook")
    public ResponseEntity<BaseResponse> editBook(@RequestBody BookRequest bookRequest) {
        try {
            Book book = bookService.editBook(bookRequest);
            return ResponseCreator.createResponseSuccess(book);
        } catch (BookException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

    @Authorized(role = "ADMIN")
    @DeleteMapping("/books/deleteBook/{id}")
    public ResponseEntity<BaseResponse> deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBook(id);
            return ResponseCreator.createResponseSuccess("Delete book has id: " + id + " success");
        } catch (BookException e) {
            return ResponseCreator.createResponseError(e.getCode(), e.getMessage());
        }
    }

}
