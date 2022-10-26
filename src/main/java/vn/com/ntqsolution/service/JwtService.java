package vn.com.ntqsolution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.constant.ResponseCode;
import vn.com.ntqsolution.domain.Admin;
import vn.com.ntqsolution.domain.RootAccount;
import vn.com.ntqsolution.exception.AdminException;
import vn.com.ntqsolution.repository.AdminRepository;
import vn.com.ntqsolution.request.JwtRequest;
import vn.com.ntqsolution.response.JwtResponse;
import vn.com.ntqsolution.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    RootAccount rootAccount;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) {
        String email = jwtRequest.getEmail();
        String userPassword = jwtRequest.getUserPassword();

        Map<String, Object> account = new HashMap<>();

        System.out.println(bCryptPasswordEncoder.encode(userPassword));

        if (rootAccount.getEmail().equals(email) && rootAccount.getPassword().equals(userPassword)) {
            account.put("email", email);
            account.put("role", "ROOT");

        } else {

            Admin admin = adminRepository.findByEmail(email);
            if (admin == null) {
                throw new AdminException("Account don't exist", ResponseCode.ADMIN_NOT_FOUND);
            }
            if (!bCryptPasswordEncoder.matches(userPassword, admin.getPassword())) {
                throw new AdminException("Password wrong", ResponseCode.WRONG_PASS);
            }
            account.put("email", email);
            account.put("role", "ADMIN");
        }
        String newGeneratedToken = JwtUtil.generateToken(account);
        return new JwtResponse(newGeneratedToken, email, (String) account.get("role"));
    }

}
