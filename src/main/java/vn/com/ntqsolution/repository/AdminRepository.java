package vn.com.ntqsolution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.ntqsolution.domain.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
    public Admin findByEmail(String email);
}
