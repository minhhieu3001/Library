package vn.com.ntqsolution.exception;

public class BookException extends RuntimeException {
    private int code;

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
