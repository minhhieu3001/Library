package vn.com.ntqsolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.User;
import vn.com.ntqsolution.exception.UserException;
import vn.com.ntqsolution.repository.UserRepository;
import vn.com.ntqsolution.request.UserRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public User addUser(UserRequest userRequest) {
        Optional<User> optional = userRepository.findById(userRequest.getId());
        if (optional.isPresent()) {
            throw new UserException("User existed", ResponseCode.USER_EXISTED);
        }
        User user = new User(userRequest.getId(), userRequest.getName(), userRequest.getEmail());
        userRepository.save(user);
        return user;
    }

    @Override
    public User editUser(UserRequest userRequest) {
        Optional<User> option = userRepository.findById(userRequest.getId());
        if(option.isPresent()) {
            User user = new User(userRequest.getId(), userRequest.getName(), userRequest.getEmail(),
                    userRequest.getNewestBorrowDate(), userRequest.getBorrowTime());
            userRepository.save(user);
            return user;
        } else throw new UserException("USER NOT FOUND", ResponseCode.USER_NOT_FOUND);
    }

    @Override
    public void deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        } else throw new UserException("USER NOT FOUND", ResponseCode.USER_NOT_FOUND);
    }

    @Override
    public List<User> searchAndSort(String searchBy, String searchValue, String sortBy, String sortValue, int pageNumber, int pageSize) {
        return (List<User>) userRepository.searchAndSort(searchBy, searchValue, sortBy, sortValue, User.class, pageNumber, pageSize);
    }
}
