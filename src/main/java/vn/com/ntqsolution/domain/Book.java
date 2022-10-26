package vn.com.ntqsolution.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Book")
public class Book {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "author")
    private String author;

    @Field(name = "publish_date")
    private String publishDate;

    @Field(name = "price")
    private Double price;

    public Book() {
    }

    public Book(String id, String name, String author, String publishDate, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
