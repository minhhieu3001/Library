package vn.com.ntqsolution.exception;

public class BorrowInformationException extends RuntimeException {
    private int code;

    public BorrowInformationException(String message) {
        super(message);
    }

    public BorrowInformationException(String message, int code) {
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
