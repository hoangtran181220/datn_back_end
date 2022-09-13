package do_an.common;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CommonUtils {

    public static final String YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";


    public static ResponseEntity getResponseFromByte(String fileName, byte[] exportInputStream) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("File", fileName);
        headers.set("Content-Disposition", "attachment; filename=" + fileName);
        headers.set("Access-Control-Expose-Headers", "File");
        return ResponseEntity.ok().headers(headers).body(exportInputStream);
    }

    public static String hmacSha512(String data, String secret) {
        try {
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
            Mac sha512Hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA512");
            sha512Hmac.init(secretKey);
            byte[] signedBytes = sha512Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(signedBytes);
        } catch (Exception var6) {
            return null;
        }
    }

    public static InputStream getInputStreamByFileName(String fileName) {
        try {
            if (!StringUtils.isEmpty(fileName) && fileName.contains(".")) {
                return new ClassPathResource("/templates/" + fileName).getInputStream();
            } else {
                return null;
            }
        } catch (IOException ioE) {
//      logCommon.error("Error Get Input Stream for : " + Constants.TEMPLATE_PATH + fileName + " is : " + ioE.toString(), ioE);
            return null;
        }
    }


    public static String getLoginUser() {
        Object rs = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (rs.getClass() == String.class) {
            return rs.toString();
        }
        UserDetails userDetails = (UserDetails) rs;

        return userDetails.getUsername();
    }


    public static String convertCurrencyFormat(String number) {
        Double d = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,##0");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(',');
        dfs.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dfs);
        return formatter.format(d);
    }

    public static String getStringDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static void replaceText(XWPFDocument document, String key, String value) {
        XWPFRun run, nextRun;
        String runsText;
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            List<XWPFRun> runs = paragraph.getRuns();
            if (runs != null) {
                for (int i = 0; i < runs.size(); i++) {
                    run = runs.get(i);
                    runsText = run.getText(0);

                    if (runsText == null) {
                        continue;
                    }

                    if (runsText.contains("${") || (runsText.contains("$") && runs.size() > (i + 1) && runs.get(i + 1).getText(0).substring(0, 1).equals("{"))) {
                        while (!runsText.contains("}")) {
                            nextRun = runs.get(i + 1);
                            runsText = runsText + nextRun.getText(0);
                            paragraph.removeRun(i + 1);
                        }
                        if (runsText.contains(getKey(key))) {
                            replaceText(run, value);
                        } else {
                            run.setText(runsText, 0);
                        }
                    }
                }
            }
        }


        for (XWPFTable tbl : document.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {

                forTable(row.getTable(), key, value);

                for (XWPFTableCell cell : row.getTableCells()) {

                    forTables(cell.getTables(), key, value);

                    for (XWPFParagraph p : cell.getParagraphs()) {

                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (int i = 0; i < runs.size(); i++) {
                                run = runs.get(i);
                                runsText = run.getText(0);

                                if (runsText == null) {
                                    continue;
                                }

                                if (runsText.contains("${") || (runsText.contains("$") && runs.size() > (i + 1) && runs.get(i + 1).getText(0).substring(0, 1).equals("{"))) {
                                    while (!runsText.contains("}")) {
                                        nextRun = runs.get(i + 1);
                                        runsText = runsText + nextRun.getText(0);
                                        p.removeRun(i + 1);
                                    }
                                    if (runsText.contains(getKey(key))) {
                                        replaceText(run, value);
                                    } else {
                                        run.setText(runsText, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void forTables(List<XWPFTable> tbls, String key, String value) {
        XWPFRun run, nextRun;
        String runsText;

        for (XWPFTable tbl : tbls) {
            for (XWPFTableRow row : tbl.getRows()) {

                forTable(row.getTable(), key, value);

                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {

                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (int i = 0; i < runs.size(); i++) {
                                run = runs.get(i);
                                runsText = run.getText(0);

                                if (runsText == null) {
                                    continue;
                                }

                                if (runsText.contains("${") || (runsText.contains("$") && runs.size() > (i + 1) && runs.get(i + 1).getText(0).substring(0, 1).equals("{"))) {
                                    while (!runsText.contains("}")) {
                                        nextRun = runs.get(i + 1);
                                        runsText = runsText + nextRun.getText(0);
                                        p.removeRun(i + 1);
                                    }
                                    if (runsText.contains(getKey(key))) {
                                        replaceText(run, value);
                                    } else {
                                        run.setText(runsText, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void forTable(XWPFTable tbl, String key, String value) {
        XWPFRun run, nextRun;
        String runsText;
        for (XWPFTableRow row : tbl.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph p : cell.getParagraphs()) {

                    List<XWPFRun> runs = p.getRuns();
                    if (runs != null) {
                        for (int i = 0; i < runs.size(); i++) {
                            run = runs.get(i);
                            runsText = run.getText(0);

                            if (runsText == null) {
                                continue;
                            }

                            if (runsText.contains("${") || (runsText.contains("$") && runs.size() > (i + 1) && runs.get(i + 1).getText(0).substring(0, 1).equals("{"))) {
                                while (!runsText.contains("}")) {
                                    nextRun = runs.get(i + 1);
                                    runsText = runsText + nextRun.getText(0);
                                    p.removeRun(i + 1);
                                }
                                if (runsText.contains(getKey(key))) {
                                    replaceText(run, value);
                                } else {
                                    run.setText(runsText, 0);
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private static void replaceText(XWPFRun run, String data) {
        if (data.contains("\n")) {
            String[] lines = data.split("\n");
            run.setText(lines[0], 0); // set first line into XWPFRun
            for (int i = 1; i < lines.length; i++) {
                // add break and insert new text
                run.addBreak();
                run.setText(lines[i]);
            }
        } else {
            run.setText(data, 0);
        }
    }

    private static String getKey(String key) {
        return "${" + key + "}";
    }

    public static File saveFile(MultipartFile file) throws IOException {
        String path = "./upload_file/" + file.getOriginalFilename();
        File dest = new File(path);
        FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
        return dest;
    }

    public static Date convertToDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        return cal.getTime();
    }

    public static Date convertFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String convertBreakLine(String value) {
        if (value == null) {
            return "";
        }
        return value.replaceAll("\n", "<br>");
    }

    public static String getLikeCondition(String str) {
        if (str == null) {
            return null;
        }
        if (!str.trim().isEmpty()) {
            String newStr =
                    str.trim()
                            .replace("\\", "\\\\")
                            .replace("\\t", "\\\\t")
                            .replace("\\n", "\\\\n")
                            .replace("\\r", "\\\\r")
                            .replace("\\z", "\\\\z")
                            .replace("\\b", "\\\\b")
                            .replaceAll("_", "\\\\_")
                            .replaceAll("%", "\\\\%");
            str = "%".concat(newStr.trim()).concat("%");
        }
        return str;
    }

    public static <T> T trimObject(T obj) {
        Logger log = LoggerFactory.getLogger(do_an.common.CommonUtils.class);
        Class objClass = obj.getClass();

        Field[] fieldsDesc = objClass.getDeclaredFields();
        if (fieldsDesc != null) {
            for (Field field : fieldsDesc) {
                try {
                    if (field.getType() == String.class) {
                        Object objSrc = objClass.getMethod("get" + StringUtils.capitalize(field.getName())).invoke(obj);
                        if (objSrc != null) {
                            objClass.getMethod("set" + StringUtils.capitalize(field.getName()), field.getType()).invoke(obj, objSrc.toString().trim());
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return obj;
    }

    /**
     * @param value
     * @return
     */
    public static Long getLongValue(Object value) {
        if (value != null && !value.toString().equals("")) {
            return (Double.valueOf(value.toString())).longValue();
        } else {
            return 0L;
        }
    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);

        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    public static String getFileName(String preName, String type) {
        String pattern = YYYYMMDD_HHMMSS;
        DateTimeFormatter dft = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        if (type != null) {
            return preName + "_" + dft.format(now).substring(2) + "." + type;
        } else {
            return preName + "_" + dft.format(now).substring(2);
        }
    }

    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
    }

    public static Date convertStringToDate(String date) throws Exception {
        if (date != null && !date.trim().isEmpty()) {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            return dateFormat.parse(date);
        } else {
            return null;
        }
    }
}

