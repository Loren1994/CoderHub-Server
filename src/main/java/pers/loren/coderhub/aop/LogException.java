package pers.loren.coderhub.aop;

public class LogException extends RuntimeException {
    private String message;

    public LogException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
