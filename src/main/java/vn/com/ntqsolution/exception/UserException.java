package vn.com.ntqsolution.exception;

public class UserException extends RuntimeException {
    private int code;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, int code) {
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
