package vn.com.ntqsolution.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.ntqsolution.domain.BorrowedInformation;

public interface BorrowedInformationRepository extends MongoRepository<BorrowedInformation, String> {

    public BorrowedInformation findByUserId(String userId);

    public void deleteByUserId(String userId);

}
