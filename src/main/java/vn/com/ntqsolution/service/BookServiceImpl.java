package vn.com.ntqsolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.Book;
import vn.com.ntqsolution.exception.BookException;
import vn.com.ntqsolution.repository.BookRepository;
import vn.com.ntqsolution.request.BookRequest;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(String id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BookException("Not found Book", ResponseCode.BOOK_NOT_FOUND);
    }

    @Override
    public Book addBook(BookRequest bookRequest) {
        Optional<Book> book = bookRepository.findById(bookRequest.getId());
        if (book.isPresent()) {
            throw new BookException("Book existed", ResponseCode.BOOK_EXISTED);
        }
        Book newBook = new Book(bookRequest.getId(), bookRequest.getName(),
                bookRequest.getAuthor(), bookRequest.getPublishDate(), bookRequest.getPrice());
        bookRepository.save(newBook);
        return newBook;
    }

    @Override
    public Book editBook(BookRequest bookRequest) {
        Book book = new Book(bookRequest.getId(), bookRequest.getName(), bookRequest.getAuthor(),
                bookRequest.getPublishDate(), bookRequest.getPrice());
        bookRepository.save(book);
        return book;
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchAndSort(String searchByFieldName, String searchValue, String sortByFieldName,
                                    String sortValue, int pageNumber, int pageSize) {
        return (List<Book>) bookRepository.searchAndSort(searchByFieldName, searchValue, sortByFieldName,
                sortValue, Book.class, pageNumber, pageSize);
    }
}
