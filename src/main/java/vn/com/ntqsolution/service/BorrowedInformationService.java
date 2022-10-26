package vn.com.ntqsolution.service;

import vn.com.ntqsolution.domain.BorrowedInformation;
import vn.com.ntqsolution.request.BorrowRequest;

import java.util.List;

public interface BorrowedInformationService {

    public List<BorrowedInformation> getAll();

    public BorrowedInformation getById(String id);

    public List<BorrowedInformation> getAllByUserId(String id);

    public BorrowedInformation getByBookId(String id);

    public BorrowedInformation add(BorrowRequest borrowRequest);

    public BorrowedInformation edit(BorrowRequest borrowRequest);

    public void deleteById(String id);
}
