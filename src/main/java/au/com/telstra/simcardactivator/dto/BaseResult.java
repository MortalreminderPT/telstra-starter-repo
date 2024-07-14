package au.com.telstra.simcardactivator.dto;

public class BaseResult<T> {
    private int code;
    private String message;
    private T data;

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(200, "Success", data);
    }

    public static <T> BaseResult<T> error(int code, String message) {
        return new BaseResult<>(code, message, null);
    }
}
