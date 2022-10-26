package vn.com.ntqsolution.request;

import vn.com.ntqsolution.domain.User;

public class UserRequest {
    private String id;
    private String name;
    private String email;
    private long newestBorrowDate;
    private int borrowTime;

    public UserRequest() {
    }

    public UserRequest(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.newestBorrowDate = 0;
        this.borrowTime = 0;
    }

    public UserRequest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.newestBorrowDate = user.getNewestBorrowDate();
        this.borrowTime = user.getBorrowTime();
    }

    public UserRequest(String id, String name, String email, long borrowDate, int borrowTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.newestBorrowDate = borrowDate;
        this.borrowTime = borrowTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNewestBorrowDate() {
        return newestBorrowDate;
    }

    public void setNewestBorrowDate(long newestBorrowDate) {
        this.newestBorrowDate = newestBorrowDate;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }
}
