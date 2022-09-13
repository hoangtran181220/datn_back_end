package do_an.common;

import lombok.Data;


@Data
public class ObjectError {
    private String code;
    private String message;

    public ObjectError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
