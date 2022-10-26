package vn.com.ntqsolution.service;

import vn.com.ntqsolution.domain.Book;
import vn.com.ntqsolution.request.BookRequest;

import java.util.List;

public interface BookService {
    public List<Book> getBooks();

    public Book getBook(String id);

    public Book addBook(BookRequest bookRequest);

    public Book editBook(BookRequest bookRequest);

    public void deleteBook(String id);

    public List<Book> searchAndSort(String searchByFieldName, String searchValue, String sortByFieldName,
                                    String sortValue, int pageNumber, int pageSize);

}
