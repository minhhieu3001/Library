package vn.com.ntqsolution.request;

import vn.com.ntqsolution.domain.BorrowedInformation;

public class BorrowRequest {

    private String id;
    private String bookId;
    private String userId;
    private String adminId;
    private int borrowTime;

    public BorrowRequest() {
    }

    public BorrowRequest(BorrowedInformation borrow) {
        this.id = borrow.getId();
        this.bookId = borrow.getBookId();
        this.userId = borrow.getUserId();
        this.adminId = borrow.getAdminId();
        this.borrowTime = borrow.getBorrowTime();
    }

    public BorrowRequest(String id, String bookId, String userId, String adminId, int borrowTime) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.adminId = adminId;
        this.borrowTime = borrowTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }
}
