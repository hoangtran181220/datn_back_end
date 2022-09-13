package do_an.common;

import do_an.common.Constants;
import do_an.common.ObjectError;

/**
 * @author : Tulv-IIST
 * @since : 6/18/2020
 **/


public class ErrorCode {

    public static final String OK = "200";

    public static final ObjectError USERNAME_NOT_FOUND = new ObjectError("BK0000", Constants.RESPONSE_MESSAGE.NOT_FOUND);

    public static final ObjectError NOT_FOUND = new ObjectError("EGP003", Constants.RESPONSE_MESSAGE.NOT_FOUND);

    public static final ObjectError AUTHENTICATION_FAILED = new ObjectError("BK0015", Constants.RESPONSE_MESSAGE.LOGIN_FAILE);
    public static final ObjectError UPDATED_OK = new ObjectError("OK", Constants.RESPONSE_MESSAGE.UPDATED_OK);

    public static final ObjectError SELECT_FAIL = new ObjectError("EX006", Constants.RESPONSE_MESSAGE.SELECT_FAIL);

}
