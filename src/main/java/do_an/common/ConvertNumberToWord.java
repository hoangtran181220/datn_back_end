package do_an.common;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ConvertNumberToWord {

    private static boolean gblnflag;

    public static String FORMAT_DECIMAL_1 = "##.";
    public static String FORMAT_DECIMAL_2 = "##.00";
    public static class CURRENCY_CODE {

        public static final String VND = "VND";
        public static final String USD = "USD";
        public static final String EUR = "EURO";
        public static final String JPY = "JPY";
        public static final String CAD = "CAD";

    }
    public static String numberToWord(String number){
        BigDecimal bigDecimal = new BigDecimal(number);
    return numberToWord(bigDecimal,"đồng", "", "", CURRENCY_CODE.VND);
    }
    /**
     * Description: Chuyen tu so sang chu
     *
     * @param pstrUnit_Name:    Don vi tinh (Dong)
     * @param pstrSubUnit_Name: Don vi tinh sau dau phay
     * @param pstrCurr:         tu sau cung (chan)
     * @return Dang chu cua so
     * @throws
     * <p>
     *             func used: numberToWord1, numberToWord2, numberToWord3
     *             </p>
     *             <p>
     *             table used:
     *             </p>
     *             <p>
     *             class used:
     *             </p>
     */
    public static String numberToWord(BigDecimal pdblNumberInput, String pstrUnit_Name, String pstrSubUnit_Name,
                                      String pstrCurr, String currency) {
        String vstrWords_Dec, vstrNumber9, vstrWords_Number;
        String vstrWord_Sign = "", vstrWords_result;
        // String vstrRet;
        int vintWords_Len, vintPos;
        // TuNK - Start update

        // Cho phep doc so tien dong sau dau phay
        BigDecimal pdblNumber = pdblNumberInput.stripTrailingZeros(); // lay data kieu so, bo so 0 sau dau .
        String[] tmp = pdblNumber.toString().split("\\.");
        String reguler = FORMAT_DECIMAL_1;
        if (tmp.length < 2) {
            tmp = (pdblNumber.toString() + ".0").split("\\.");
            for (int i = 0; i < tmp[1].length(); i++) {
                reguler += "0";
            }
        } else if (tmp[1].toString().length() > 0) {
            for (int i = 0; i < tmp[1].length(); i++) {
                reguler += "0";
            }
        }

        if (pdblNumber.doubleValue() < 0) {
            vstrWord_Sign = "Trừ ";
            vstrWords_Number = format(pdblNumber.negate(), reguler);
        } else if (pdblNumber.doubleValue() > 0) {
            vstrWords_Number = format(pdblNumber, reguler);
        } else {
            return "Không " + pstrUnit_Name;
        }
        // TuNK - Start update

        vintPos = ("" + vstrWords_Number).indexOf('.');
        if (vintPos == -1) {
            vintPos = ("" + vstrWords_Number).indexOf(',');
        }

        vstrWords_Dec = ("" + vstrWords_Number).substring(vintPos + 1);
        vstrWords_Number = ("" + vstrWords_Number).substring(0, vintPos);
        vintWords_Len = ("" + vstrWords_Number).length();

        vstrNumber9 = vintWords_Len < 9 ? vstrWords_Number : ("" + vstrWords_Number).substring(vintWords_Len - 9);

        vstrWords_result = numberToWord1(vstrNumber9);

        vintWords_Len = vintWords_Len - 9;
        while (vintWords_Len > 0) {
            vstrWords_Number = ("" + vstrWords_Number).substring(0, vintWords_Len);
            vstrNumber9 = vintWords_Len < 9 ? vstrWords_Number : ("" + vstrWords_Number).substring(vintWords_Len - 9);
            gblnflag = true;
            vstrWords_result = numberToWord1(vstrNumber9) + " tỷ" + vstrWords_result;
            vintWords_Len = vintWords_Len - 9;
        }

        if (Integer.parseInt(vstrWords_Dec) == 0) {
            vstrWords_result = vstrWords_result + " " + pstrUnit_Name + pstrCurr;
        } else if (!pstrSubUnit_Name.isEmpty()) {
            // minh_esct đọc tiền bắt đầu từ dấu phẩy là 10 xu cho tất cả loại tiền khác
            // if (Constants.CURRENCY_CODE.USD.equals(currency) ||
            // Constants.CURRENCY_CODE.EUR.equals(currency)) {
            if (vstrWords_Dec.length() > 2) {
                String newString = vstrWords_Dec.substring(0, 2) + "." + vstrWords_Dec.substring(2);
                vstrWords_result = vstrWords_result + " " + pstrUnit_Name + " và "
                        + ConvertNumberToWord.numberToWord(new BigDecimal(newString), "xu", "", "", null).toLowerCase();
            } else {
                // 2 so sau dau phay
                if (vstrWords_Dec.length() == 1) {
                    vstrWords_Dec = vstrWords_Dec + 0;
                }
                vstrWords_result = vstrWords_result + " " + pstrUnit_Name + " và" + numberToWord1(vstrWords_Dec) + " "
                        + pstrSubUnit_Name + " " + pstrCurr;
            }
            // } else {
            // vstrWords_result = vstrWords_result + " " + pstrUnit_Name + " và " +
            // numberToWord1(vstrWords_Dec) + " " + pstrSubUnit_Name + " " + pstrCurr;
            // }
        } else {
            vstrWords_result = vstrWords_result + " phẩy " + numberToWord1(vstrWords_Dec) + " " + pstrUnit_Name + " "
                    + pstrCurr;
        }

        // End If
        vstrWords_result = vstrWords_result.trim();
        return vstrWord_Sign + vstrWords_result.substring(0, 1).toUpperCase() + vstrWords_result.substring(1);
    }

    // Halt_esct Update thêm ngoại tệ EUR và JPY
    // minh_esct update 04-09-2019 : Bổ sung đọc tiền theo cách bình thường với
    // CurrencyBO
    public static String numberToWord(BigDecimal pdblNumber, String currencyCode) {
        if (StringUtils.isEmpty(currencyCode)) {
            return ConvertNumberToWord.numberToWord(pdblNumber, "đồng", "", "", CURRENCY_CODE.VND);
        } else {
            switch (currencyCode) {
                case CURRENCY_CODE.USD:
                    return ConvertNumberToWord.numberToWord(pdblNumber, "đô la Mỹ", "xu", "", currencyCode);
                case CURRENCY_CODE.EUR:
                    return ConvertNumberToWord.numberToWord(pdblNumber, "Euro", "xu", "", currencyCode);
                case CURRENCY_CODE.JPY:
                    return ConvertNumberToWord.numberToWord(pdblNumber, "yên Nhật", "", "", currencyCode);
                case CURRENCY_CODE.VND:
                    return ConvertNumberToWord.numberToWord(pdblNumber, "đồng", "", "", currencyCode);
                default:
                    return ConvertNumberToWord.numberToWord(pdblNumber, "đồng", "", "", CURRENCY_CODE.VND);
            }
        }
    }

    private static String numberToWord1(String pstrWords_Number1) {
        String vstrWords_result, vstrNumber123;
        int vintWords_Len;

        if (StringUtils.isEmpty(pstrWords_Number1)) {
            return "Không";
        }

        if (Integer.parseInt(pstrWords_Number1) == 0) {
            return "";
        }

        pstrWords_Number1 = pstrWords_Number1.trim();
        vintWords_Len = ("" + pstrWords_Number1).length();
        if (vintWords_Len > 9) {
            return "";
        }

        vstrNumber123 = vintWords_Len < 3 ? pstrWords_Number1 : ("" + pstrWords_Number1).substring(vintWords_Len - 3);
        vstrWords_result = numberToWord2(vstrNumber123);

        vintWords_Len = vintWords_Len - 3;
        if (vintWords_Len <= 0) {
            return vstrWords_result;
        }
        pstrWords_Number1 = ("" + pstrWords_Number1).substring(0, vintWords_Len);
        vstrNumber123 = vintWords_Len < 3 ? pstrWords_Number1 : ("" + pstrWords_Number1).substring(vintWords_Len - 3);
        if (Integer.parseInt(vstrNumber123) > 0) {
            gblnflag = true;
            vstrWords_result = numberToWord2(vstrNumber123) + " nghìn" + vstrWords_result;
        }

        vintWords_Len = vintWords_Len - 3;
        if (vintWords_Len <= 0) {
            return vstrWords_result;
        }

        pstrWords_Number1 = ("" + pstrWords_Number1).substring(0, vintWords_Len);
        vstrNumber123 = vintWords_Len < 3 ? pstrWords_Number1 : ("" + pstrWords_Number1).substring(vintWords_Len - 3);
        if (Integer.parseInt(vstrNumber123) > 0) {
            gblnflag = true;
            vstrWords_result = numberToWord2(vstrNumber123) + " triệu" + vstrWords_result;
        }
        return vstrWords_result;

    }

    private static String numberToWord2(String pstrWords_Number2) {
        String vstrHundreds_Name, vstrTens_Name, vstrUnits_Name;
        String vstrWord_Number1 = "", vstrWord_Number2 = "", vstrWord_Number3 = "";
        int vintWords_Len;

        if (Integer.parseInt(pstrWords_Number2) == 0) {
            return "";
        }

        vintWords_Len = ("" + pstrWords_Number2).length();
        vstrHundreds_Name = "";
        vstrTens_Name = "";
//        vstrUnits_Name = "";

        switch (vintWords_Len) {
            case 3:
                vstrWord_Number1 = pstrWords_Number2.substring(0, 1);
                vstrWord_Number2 = pstrWords_Number2.substring(1, 2);
                vstrWord_Number3 = pstrWords_Number2.substring(2, 3);
                break;
            case 2:
                vstrWord_Number2 = pstrWords_Number2.substring(0, 1);
                vstrWord_Number3 = pstrWords_Number2.substring(1, 2);
                break;
            case 1:
                vstrWord_Number3 = pstrWords_Number2.substring(0, 1);
                break;
        }

        if (!vstrWord_Number1.isEmpty()) {
            vstrHundreds_Name = numberToWord3(vstrWord_Number1) + " trăm";
        }

        if (!vstrWord_Number2.isEmpty()) {
            switch (vstrWord_Number2.charAt(0)) {
                case '0':
                    if (!"0".equals(vstrWord_Number3) && vintWords_Len > 2) {
                        vstrTens_Name = " lẻ";
                    } else {
                        vstrTens_Name = "";
                    }
                    break;
                case '1':
                    vstrTens_Name = " mười";
                    break;
                default:
                    vstrTens_Name = numberToWord3(vstrWord_Number2) + " mươi";
            }
        }

        switch (vstrWord_Number3.charAt(0)) {
            case '0':
                vstrUnits_Name = "";
                break;
            case '1':
                if (gblnflag) {
                    if (vstrWord_Number2.isEmpty()) {
                        vstrUnits_Name = " một";
                    } else if (vstrWord_Number2.charAt(0) == '1' || vstrWord_Number2.charAt(0) == '0') {
                        vstrUnits_Name = " một";
                    } else {
                        vstrUnits_Name = " mốt";
                    }
                    gblnflag = false;
                } else if (vintWords_Len == 1) {
                    vstrUnits_Name = " một";
                } else if (vstrWord_Number2.isEmpty()) {
                    vstrUnits_Name = " một";
                } else if (vstrWord_Number2.charAt(0) == '1' || vstrWord_Number2.charAt(0) == '0') {
                    vstrUnits_Name = " một";
                } else {
                    vstrUnits_Name = " mốt";
                }
                break;

            case '5':
                if (!"0".equals(vstrWord_Number2) && vintWords_Len > 1) {
                    vstrUnits_Name = " lăm";
                } else {
                    vstrUnits_Name = " năm";
                }
                break;

            default:
                vstrUnits_Name = numberToWord3(vstrWord_Number3);
        }
        return vstrHundreds_Name + vstrTens_Name + vstrUnits_Name;
    }

    private static String numberToWord3(String pstrWord_Number) {
        switch (pstrWord_Number.charAt(0)) {
            case '0':
                return " không";
            case '1':
                return " một";
            case '2':
                return " hai";
            case '3':
                return " ba";
            case '4':
                return " bốn";
            case '5':
                return " năm";
            case '6':
                return " sáu";
            case '7':
                return " bảy";
            case '8':
                return " tám";
            case '9':
                return " chín";
        }
        return "";
    }

    public static String format(BigDecimal dblNumber, String strPattern) {
        if(dblNumber == null) {
            dblNumber = BigDecimal.ZERO;
        }
        DecimalFormat fmt = new DecimalFormat(strPattern);
        return fmt.format(dblNumber);
    }
}
