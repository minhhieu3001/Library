package vn.com.ntqsolution.response;

import lombok.Data;

@Data
public class AdminResponse {
    private String username;
    private String originPassword;
    private String email;

    public AdminResponse() {
    }

    public AdminResponse(String username, String originPassword, String email) {
        this.username = username;
        this.originPassword = originPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
