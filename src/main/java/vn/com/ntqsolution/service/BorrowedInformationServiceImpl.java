package vn.com.ntqsolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.Admin;
import vn.com.ntqsolution.domain.Book;
import vn.com.ntqsolution.domain.BorrowedInformation;
import vn.com.ntqsolution.domain.User;
import vn.com.ntqsolution.exception.BorrowInformationException;
import vn.com.ntqsolution.repository.BorrowedInformationRepository;
import vn.com.ntqsolution.request.BorrowRequest;
import vn.com.ntqsolution.request.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowedInformationServiceImpl implements BorrowedInformationService {

    @Autowired
    BorrowedInformationRepository borrowedInformationRepository;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Override
    public List<BorrowedInformation> getAll() {
        return borrowedInformationRepository.findAll();
    }


    @Override
    public BorrowedInformation getById(String id) {
        Optional<BorrowedInformation> optional = borrowedInformationRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BorrowInformationException("Borrow Information not found", ResponseCode.BORROWINFORMATION_NOT_FOUND);
    }

    @Override
    public BorrowedInformation getByBookId(String id) {
        return null;
    }

    @Override
    public List<BorrowedInformation> getAllByUserId(String id) {
        List<BorrowedInformation> rs = new ArrayList<>();
        if (rs == null) {
            throw new BorrowInformationException("Borrow Information not found", ResponseCode.BORROWINFORMATION_NOT_FOUND);
        }
        return rs;
    }

    @Override
    public BorrowedInformation add(BorrowRequest borrowRequest) {
        Optional<BorrowedInformation> optional = borrowedInformationRepository.findById(borrowRequest.getId());
        if (optional.isPresent()) {
            throw new BorrowInformationException("Borrow Information existed", ResponseCode.BORROWINFORMATION_EXISTED);
        }

        User user = userService.getUser(borrowRequest.getUserId());
        if (user == null) {
            throw new BorrowInformationException("Borrow Information can't find user", ResponseCode.USER_NOT_FOUND);
        }

        Admin admin = adminService.getAdmin(borrowRequest.getAdminId());
        if (admin == null) {
            throw new BorrowInformationException("Borrow Information can't find admin", ResponseCode.ADMIN_NOT_FOUND);
        }

        Book book = bookService.getBook(borrowRequest.getBookId());
        if (book == null) {
            throw new BorrowInformationException("Borrow Information can't find book", ResponseCode.BOOK_NOT_FOUND);
        }

        long borrowDate = System.currentTimeMillis();

        BorrowedInformation borrowedInformation = new BorrowedInformation(borrowRequest.getId(),
                borrowRequest.getBookId(), borrowRequest.getUserId(), borrowRequest.getAdminId(),
                borrowDate, borrowRequest.getBorrowTime());

        borrowedInformationRepository.save(borrowedInformation);

        user.setNewestBorrowDate(borrowDate);
        user.setBorrowTime(borrowRequest.getBorrowTime());
        userService.editUser(new UserRequest(user));

        return borrowedInformation;
    }

    @Override
    public BorrowedInformation edit(BorrowRequest borrowRequest) {
        Optional<BorrowedInformation> optional = borrowedInformationRepository.findById(borrowRequest.getId());
        if (!optional.isPresent()) {
            throw new BorrowInformationException("Borrow Information not found", ResponseCode.BORROWINFORMATION_NOT_FOUND);
        }
        User user = userService.getUser(borrowRequest.getUserId());
        BorrowedInformation borrowedInformation = new BorrowedInformation(borrowRequest.getId(), borrowRequest.getBookId(),
                borrowRequest.getUserId(), borrowRequest.getAdminId(), optional.get().getBorrowDate(), borrowRequest.getBorrowTime());
        borrowedInformationRepository.save(borrowedInformation);
        user.setBorrowTime(borrowRequest.getBorrowTime());
        userService.editUser(new UserRequest(user));
        return borrowedInformation;
    }

    @Override
    public void deleteById(String id) {
        borrowedInformationRepository.deleteById(id);
    }
}
