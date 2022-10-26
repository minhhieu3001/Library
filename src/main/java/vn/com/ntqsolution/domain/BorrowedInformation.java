package vn.com.ntqsolution.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Borrowed Information")
public class BorrowedInformation {

    @Id
    private String id;

    @Field(name = "bookId")
    private String bookId;

    @Field(name = "userId")
    private String userId;

    @Field(name = "adminId")
    private String adminId;

    @Field(name = "borrrowDate")
    private long borrowDate;

    @Field(name = "borrowTime")
    private int borrowTime;

    public BorrowedInformation() {
    }

    public BorrowedInformation(String id, String bookId, String userId, String adminId, long borrowDate, int borrowTime) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.adminId = adminId;
        this.borrowDate = borrowDate;
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

    public long getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(int borrowTime) {
        this.borrowTime = borrowTime;
    }
}
