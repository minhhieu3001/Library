package vn.com.ntqsolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.Admin;
import vn.com.ntqsolution.exception.AdminException;
import vn.com.ntqsolution.repository.AdminRepository;
import vn.com.ntqsolution.request.AdminRequest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(AdminRequest newAdmin) {
        if (newAdmin.getEmail().equals("root@gmail.com")) {
            throw new AdminException("Duplicate root account", ResponseCode.ACCOUNT_EXISTED);
        }
        Admin optional = adminRepository.findByEmail(newAdmin.getEmail());
        Random random = new Random();
        if (optional != null) {
            throw new AdminException("Admin existed", ResponseCode.ACCOUNT_EXISTED);
        }
        String originPassword = random.ints(48, 123).limit(15)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        Admin admin = new Admin(newAdmin.getId(), newAdmin.getUsername(), bCryptPasswordEncoder.encode(originPassword),
                newAdmin.getEmail(), newAdmin.getRole());
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public Admin getAdmin(String id) {
        Optional<Admin> optional = adminRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Admin editAdmin(AdminRequest adminEdited) {
        Optional<Admin> option = adminRepository.findById(adminEdited.getId());
        if(option.isPresent()) {
            Admin admin = new Admin(adminEdited.getId(), adminEdited.getUsername(),
                    bCryptPasswordEncoder.encode(adminEdited.getPassword()),
                    adminEdited.getEmail(), adminEdited.getRole());
            adminRepository.save(admin);
            return admin;
        } else throw new AdminException("ADMIN NOT FOUND", ResponseCode.ADMIN_NOT_FOUND);
    }

    @Override
    public void deleteAdmin(String id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            adminRepository.deleteById(id);
        } else throw new AdminException("ADMIN NOT FOUND", ResponseCode.ADMIN_NOT_FOUND);
    }

    @Override
    public Admin findByEmail(String email) {
        Admin rs = adminRepository.findByEmail(email);
        if (rs == null) {
            throw new AdminException("Admin not found", ResponseCode.ADMIN_NOT_FOUND);
        }
        return rs;
    }

}
