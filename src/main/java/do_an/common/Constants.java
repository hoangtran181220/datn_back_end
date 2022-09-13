package do_an.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Constants {

    public interface CORS_FILTER {

        public static final String ALLOW_METHODS = "POST, PUT, GET, OPTIONS, DELETE";
        public static final String ALLOW_HEADERS = "*";
    }

    public interface TOKEN {
        long TIMEOUT = 1800L;
        String SECRET_KEY = "vts-ht-qltn";
        String JWT_HEADER = "{\"alg\":\"HS512\",\"typ\":\"JWT\"}";
    }

    public interface LOGGER {

        String ACTION_LOG = "vt.log.action";
        String NOTIFY_LOG = "vt.log.notify";
        String SCHEDULED_LOG = "vt.log.scheduled";
    }

    public static class RESPONSE_MESSAGE {
        public static final String LOGIN_FAILE = "Tai khoan hoac mat khau khong chinh xac";
        public static final String RESET_PASSWORD_FAIL = "Reset mật khẩu thất bại, có lỗi hệ thống xảy ra";
        public static final String NOT_FOUND = "Bản ghi không tồn tại";
        public static final String EMAIL_EXIST = "email đã tồn tại";
        public static final String ID_REQUIRED = "Id không hợp lệ";
        public static final String REQUEST_STATUS_INVALID = "Trạng thái không chính xác ";
        public static final String SELECT_FAIL = "Đã có lỗi trong quá trình xử lý, vui lòng thực hiện vào lúc khác!";
        public static final String UPDATED_OK = "Cập nhật thành công!";
        public static final String CREATED_OK = "Thêm mới thành công!";
        public static final String DELETED_OK = "Xóa đối tượng thành công!";
        public static final String NOT_FOUND_INVENTORY = "Không tìm thấy cấu hình inventory & Playbook của ";
    }

    public static final class STATE {
        public static final Integer ACTIVE = 1;
        public static final Integer DELETED = 0;
    }

    public static final String VIDEO = "/video";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String VIDEO_CONTENT = "video/";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final String BYTES = "bytes";
    public static final int BYTE_RANGE = 1024;


    public static final class APP_PARAMS {
        public static class REQUEST_SETTING_STATUS {
            public static final Integer SETUP_STATUS_1 = 1;
            public static final Integer SETUP_STATUS_2 = 2;
            public static final Integer SETUP_STATUS_3 = 3;
            public static final Integer SETUP_STATUS_4 = 4;
        }

        public static class CONNECT_SERVER_STATUS {
            public static final Integer NO_CONNECT_STATUS = 1;
            public static final Integer CONNECT_STATUS = 2;
        }

        public static class STATUS_SETTING {
            public static final Integer NOT_SETUP = 1;
            public static final Integer SUCCESS = 2;
            public static final Integer FAILED = 3;
            public static final Integer SETUP = 4;
        }

        public static class STATUS_HISTORY {
            public static final Integer NO_CONNECT = 1;
            public static final Integer CONNECT = 2;
        }

        public static class IS_OPTION_SETTING {
            public static final Integer PLAYBOOK = 1;
            public static final Integer UNINSTALL_PLAYBOOK = 2;
        }

        public static class FILE_ATTACH_TYPE {
            public static final Integer INVENTORY = 1;
            public static final Integer PLAYBOOK = 2;
            public static final Integer UNINSTALL_PLAYBOOK = 3;
            public static final Integer RESULT_SETUP = 4;
            public static final Integer EXCEL = 5;
        }
    }
}
