package do_an.common;

import org.springframework.http.HttpStatus;

public enum RespType {

	ARG_INVALID(HttpStatus.UNPROCESSABLE_ENTITY, "Argument Invalid");
	
	private HttpStatus code;
    private String msg;

    RespType(HttpStatus code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
