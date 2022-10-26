package vn.com.ntqsolution.exception;

public class AdminException extends RuntimeException {
    private int code;

    public AdminException(String message) {
        super(message);
    }

    public AdminException(String message, int code) {
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
