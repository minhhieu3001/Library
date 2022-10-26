package vn.com.ntqsolution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.ntqsolution.domain.User;

public interface UserRepository extends MongoRepository<User, String>, SearchAndSortRepository {
}
