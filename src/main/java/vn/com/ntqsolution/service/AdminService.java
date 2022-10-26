package vn.com.ntqsolution.service;

import vn.com.ntqsolution.domain.Admin;
import vn.com.ntqsolution.request.AdminRequest;

import java.util.List;

public interface AdminService {
    public List<Admin> getAdmins();

    public Admin getAdmin(String id);

    public Admin addAdmin(AdminRequest newAdmin);

    public Admin editAdmin(AdminRequest adminEdited);

    public void deleteAdmin(String id);

    public Admin findByEmail(String email);

}
