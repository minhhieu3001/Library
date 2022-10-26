package vn.com.ntqsolution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.ntqsolution.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, SearchAndSortRepository {
    
    public Book findByName(String name);
}
