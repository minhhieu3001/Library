package vn.com.ntqsolution.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "User")
public class User {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "email")
    private String email;

    @Field(name = "newestBorrowDate")
    private long newestBorrowDate;

    @Field(name = "timeBorrow")
    private int borrowTime;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.newestBorrowDate = 0;
        this.borrowTime = 0;
    }

    public User(String id, String name, String email, long newestBorrowDate, int borrowTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.newestBorrowDate = newestBorrowDate;
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

    public void setName(String username) {
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
