package vn.com.ntqsolution.service;

import vn.com.ntqsolution.domain.User;
import vn.com.ntqsolution.request.UserRequest;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User getUser(String id);

    public User addUser(UserRequest userRequest);

    public User editUser(UserRequest userRequest);

    public void deleteUser(String id);

    public List<User> searchAndSort(String searchByFieldName, String searchValue, String sortByFielName,
                                    String sortValue, int pageNumber, int pageSize);
}
