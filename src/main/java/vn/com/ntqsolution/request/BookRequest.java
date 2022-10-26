package vn.com.ntqsolution.request;

import vn.com.ntqsolution.domain.Book;

public class BookRequest {
    private String id;
    private String name;
    private String author;
    private String publishDate;
    private Double price;


    public BookRequest() {
    }

    public BookRequest(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.publishDate = book.getPublishDate();
        this.price = book.getPrice();
    }

    public BookRequest(String id, String name, String author, String publishDate, Double price) {
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
