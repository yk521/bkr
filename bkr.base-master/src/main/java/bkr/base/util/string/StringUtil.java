package bkr.base.util.string;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import bkr.base.util.constants.CommonConstants;
import bkr.base.util.constants.StringFormatConstants;

/**
 * 用于字符串处理的Util类
 * 
 * @author chengd
 */
public final class StringUtil {

    /**
     * 空字符串
     */
    private static final String EMPTY_STRING = "";

    /**
     * 全角字符
     */
    private static final String WIDECHARS = "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"
            + "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ１２３４５６７８９０";
    /**
     * 半角字符
     */
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz1234567890";

    /**
     * 全角空格
     */
    private static final char FULL_SPACE = 12288;

    /**
     * 半角空格
     */
    private static final char HALF_SPACE = 32;

    /**
     * 括号
     */
    private static final String PUNC_BRACKET = "[";

    /**
     * 转义括号
     */
    private static final String ESCAPE_BRACKET = "[[]";

    /**
     * 下划线
     */
    private static final String PUNC_UNDERLINE = "_";

    /**
     * 转义下划线
     */
    private static final String ESCAPE_UNDERLINE = "[_]";

    /**
     * 百分号
     */
    private static final String PUNC_PERCENT = "%";

    /**
     * 转义百分号
     */
    private static final String ESCAPE_PERCENT = "[%]";

    /**
     * 单引号
     */
    private static final String PUNC_SINGLE_QUOTE = "'";

    /**
     * 转义单引号
     */
    private static final String ESCAPE_SINGLE_QUOTE = "''";

    /**
     * \r
     */
    private static final String NEW_LINE = "\r";

    /**
     * \n
     */
    private static final String NEXT_LINE = "\n";

    /**
     * 数字
     */
    private static final String NUMBER_REGEX = "[0-9]*";

    /**
     * 日期格式
     */
    private static StringFormatType[] dateParsePattern;

    /**
     * 金额格式
     */
    private static StringFormatType[] numberParsePattern;

    static {
        dateParsePattern = new StringFormatType[] {
                StringFormatType.DATE_FORMAT_DATETIME,
                StringFormatType.DATE_FORMAT_TIME,
                StringFormatType.DATE_FORMAT_YEAR_MON_DAY,
                StringFormatType.DATE_FORMAT_YEAR_MON,
                StringFormatType.DATE_FORMAT_FYEAR_MON,
                StringFormatType.DATE_FORMAT_YY_MM_DD,
                StringFormatType.DATE_FORMAT_YY_M_D,
                StringFormatType.DATE_FORMAT_YYYY_MM_DD,
                StringFormatType.DATE_FORMAT_YYYY_M_D,
                StringFormatType.DATE_FORMAT_YY__MM__DD,
                StringFormatType.DATE_FORMAT_YY__M__D,
                StringFormatType.DATE_FORMAT_YYYY__MM__DD,
                StringFormatType.DATE_FORMAT_YYYY__M__D,
                StringFormatType.DATE_FORMAT_YYYY__MM__DD__HH_MM_SS_SSS,
                StringFormatType.DATE_FORMAT_YYYYMMDD,
                StringFormatType.DATE_FORMAT_YYYYMM,
                StringFormatType.DATE_FORMAT_YYYYMMDDHHMMSS,
                StringFormatType.DATE_FORMAT_YYYY_MM,
                StringFormatType.DATE_FORMAT_DDMMMYYYY,
                StringFormatType.DATE_FORMAT_DDMMMYY,
                StringFormatType.DATE_FORMAT_YYMM };

        numberParsePattern = new StringFormatType[] {
                StringFormatType.NUMBER_FORMAT_INTEGER_NUMBER,
                StringFormatType.NUMBER_FORMAT_MONEY,
                StringFormatType.NUMBER_FORMAT_NUMBER,
                StringFormatType.NUMBER_FORMAT_NORMAL_NUMBER,
                StringFormatType.NUMBER_FORMAT_PER_NUMBER,
                StringFormatType.NUMBER_FORMAT_00_NUMBER };
    }

    /**
     * 在头尾追加重复字符串
     * 
     * @param str
     *            字符串
     * @param c
     *            重复字符
     * @param len
     *            重复次数
     * @return 追加重复字符后的字符串
     */
    public static String append(String str, char c, int len) {
        return rappend(lappend(str, c, len), c, len);
    }

    /**
     * 将驼峰模式的字符串转换为下划线连接的字符串
     * 
     * @param obj
     *            字符串变量
     * @return 下划线连接的字符串
     */
    public static String camelToUnderline(String obj) {

        assertNotNull(obj);

        // 获取按驼峰模式分割的子字符串组
        String[] subStrings = obj.split("[A-Z]");

        // 返回变量
        String retval = "";

        // 格式化为下划线连接的字符串并连接每个子字符串
        for (int i = 0; i < subStrings.length - 1; i++) {
            int idx = obj.indexOf(subStrings[i], retval.length() - i)
                    + subStrings[i].length();
            retval += subStrings[i].toUpperCase() + PUNC_UNDERLINE
                    + obj.substring(idx, idx + 1);
        }

        retval += subStrings[subStrings.length - 1].toUpperCase();

        // 去掉字符串首的下划线
        if ((0 == retval.indexOf(PUNC_UNDERLINE)) && (1 < retval.length())) {
            retval = retval.substring(1);
        }

        return retval;
    }

    /**
     * 处理字符串变量的首字母
     * 
     * @param obj
     *            字符串变量
     * @param type
     *            处理类型
     * @return 处理后的字符串变量
     */
    public static String firstLetterFormat(String obj,
            StringFormatConstants type) {

        assertNotNull(type);

        String firstLetter = null;

        // 如果字符串变量为空，则直接返回
        if (isEmpty(obj)) {
            return obj;
        }

        if (StringFormatConstants.UPPER_CASE.equals(type)) {
            // 获取字符串变量的首字母的大写字母
            firstLetter = obj.substring(0, 1).toUpperCase();
        } else if (StringFormatConstants.LOWER_CASE.equals(type)) {
            // 获取字符串变量的首字母的小写字母
            firstLetter = obj.substring(0, 1).toLowerCase();
        }

        // 如果字符串变量长度大于1，则返回首字母处理后的字符串变量，
        // 否则返回处理后的首字母
        if (1 < obj.length()) {
            return firstLetter + obj.substring(1);
        } else {
            return firstLetter;
        }
    }

    /**
     * 字符串转日期
     * 
     * @param strDate
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return 日期
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (isEmpty(strDate)) {
            return null;
        }
        String fmt = pattern;
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        formatter.setLenient(false);
        try {
            return formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转日期
     * 
     * @param strDate
     *            日期字符串
     * @param formatType
     *            日期格式
     * @return 日期
     */
    public static Date stringToDate(String strDate, StringFormatType formatType) {
        return stringToDate(strDate, formatType.format());
    }

    /**
     * 格式化日期
     * 
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 格式化后的日期
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        String fmt = pattern;
        SimpleDateFormat formatter = new SimpleDateFormat(fmt);
        return formatter.format(date);
    }

    /**
     * 格式化日期
     * 
     * @param dt
     *            日期
     * @param formatType
     *            日期格式
     * @return 格式化后的日期
     */
    public static String formatDate(Date dt, StringFormatType formatType) {
        return formatDate(dt, formatType.format());
    }

    /**
     * 画面上消息参数是日期时,需要格式化日期的格式. 调用此方法,统一生成消息参数
     * 
     * @param dt
     *            日期
     * @return
     */
    public static String formatMessageParamDatee(Date dt) {
        if (dt == null)
            return "";
        return formatDate(dt, StringFormatType.DATE_FORMAT_YYYY_MM_DD);
    }

    /**
     * 格式化数字
     * 
     * @param bd
     *            数字
     * @param formatType
     *            格式类型
     * @return 格式化后的数字
     */
    public static String formatNumber(BigDecimal bd, StringFormatType formatType) {

        assertNotNull(formatType);

        if (null == bd) {
            return null;
        }

        DecimalFormat formatter = new DecimalFormat(formatType.format());
        return formatter.format(bd);
    }

    /**
     * 格式化字符串 yyyy/MM的格式
     * 
     * @param dt
     * @return
     */
    public static String formatDateString2yyyy_MM(String dt) {
        if (isEmpty(dt))
            return "";
        return dt.substring(0, 4) + "/" + dt.substring(4, 6);
    }

    /**
     * 格式化字符串 yyyyMM的格式
     * 
     * @param dt
     * @return
     */
    public static String formatDateString2yyyyMM(String dt) {
        if (isEmpty(dt))
            return "";
        dt = dt.replace("-", "");
        dt = dt.replace("/", "");
        return dt.substring(0, 4) + dt.substring(4, 6);
    }

    /**
     * 判断字符串变量是否为空
     * 
     * @param obj
     *            字符串变量
     * @return true时字符串为空
     */
    public static boolean isEmpty(String obj) {
        if (null == obj) {
            return true;
        } else {
            return EMPTY_STRING.equals(obj);
        }
    }

    /**
     * 字符串左对齐
     * 
     * @param str
     *            字符串
     * @param c
     *            填充字符
     * @param len
     *            长度
     * @return 左对齐后的字符串
     */
    public static String lalign(String str, char c, int len) {

        assertNotNull(str);

        if (str.length() >= len) {
            return str;
        }

        return str + repeat(c, len - str.length());
    }

    /**
     * 字符串左对齐，填充空格
     * 
     * @param str
     *            字符串
     * @param len
     *            长度
     * @return 左对齐后的字符串
     */
    public static String lalign(String str, int len) {
        return lalign(str, HALF_SPACE, len);
    }

    /**
     * 在头部追加重复字符串
     * 
     * @param str
     *            字符串
     * @param c
     *            重复字符
     * @param len
     *            重复次数
     * @return 追加重复字符后的字符串
     */
    public static String lappend(String str, char c, int len) {

        assertNotNull(str);
        assertNotNegative(len);

        return repeat(c, len) + str;
    }

    /**
     * 去除头部空格
     * 
     * @param str
     *            对象字符串
     * @return 去除空格后的字符串
     */
    public static String ltrim(String str) {

        assertNotNull(str);

        for (int i = 0; i < str.length(); i++) {
            if (HALF_SPACE != str.charAt(i) && FULL_SPACE != str.charAt(i)) {
                return str.substring(i);
            }
        }
        return "";
    }

    /**
     * 去除头部重复字符
     * 
     * @param str
     *            对象字符串
     * @param c
     *            重复字符
     * @return 去除重复字符后的字符串
     */
    public static String ltrim(String str, char c) {

        assertNotNull(str);

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != c) {
                return str.substring(i);
            }
        }
        return "";
    }

    /**
     * 将字符串转换为日期
     * 
     * @param dateString
     *            日期字符串
     * @return 日期
     */
    public static Date parseDate(String dateString) {

        assertNotNull(dateString);

        SimpleDateFormat parser = new SimpleDateFormat();

        Date retval = null;

        boolean chkResult = false;

        // 尝试按已知的格式转换
        for (StringFormatType pattern : dateParsePattern) {
            try {
                parser.applyPattern(pattern.format());
                retval = parser.parse(dateString);
                // 如果转换成功，则验证其结果正确性
                if (dateString.equals(StringUtil.formatDate(retval, pattern))) {
                    chkResult = true;
                    break;
                }
            } catch (ParseException pe) {
            }
        }

        // 如果验证失败，则返回null
        if (!chkResult) {
            retval = null;
        }

        return retval;
    }

    /**
     * 将字符串转换为数字
     * 
     * @param numberString
     *            数字字符串
     * @return 数字
     */
    public static BigDecimal parseNumber(String numberString) {

        assertNotNull(numberString);

        DecimalFormat parser = new DecimalFormat();

        // 设置返回BigDecimal
        parser.setParseBigDecimal(true);

        BigDecimal retval = null;

        boolean chkResult = false;

        // 尝试按已知的格式转换
        for (StringFormatType pattern : numberParsePattern) {
            try {
                parser.applyPattern(pattern.format());
                retval = (BigDecimal) parser.parse(numberString);
                if (numberString.equals(StringUtil
                        .formatNumber(retval, pattern))) {
                    chkResult = true;
                    break;
                }
            } catch (ParseException pe) {
            }
        }

        // 如果验证失败，则返回null
        if (!chkResult) {
            retval = null;
        }

        return retval;
    }

    /**
     * 字符串右对齐
     * 
     * @param str
     *            字符串
     * @param c
     *            填充字符
     * @param len
     *            长度
     * @return 右对齐后的字符串
     */
    public static String ralign(String str, char c, int len) {

        assertNotNull(str);
        assertNotNegative(len);

        if (str.length() >= len) {
            return str;
        }

        return repeat(c, len - str.length()) + str;
    }

    /**
     * 字符串右对齐，填充空格
     * 
     * @param str
     *            字符串
     * @param len
     *            长度
     * @return 右对齐后的字符串
     */
    public static String ralign(String str, int len) {
        return ralign(str, HALF_SPACE, len);
    }

    /**
     * 在尾部追加重复字符串
     * 
     * @param str
     *            字符串
     * @param c
     *            重复字符
     * @param len
     *            重复次数
     * @return 追加重复字符后的字符串
     */
    public static String rappend(String str, char c, int len) {

        assertNotNull(str);
        assertNotNegative(len);

        return str + repeat(c, len);
    }

    /**
     * 重复字符串
     * 
     * @param c
     *            重复字符
     * @param len
     *            重复次数
     * @return 重复字符串
     */
    public static String repeat(char c, int len) {

        assertNotNegative(len);

        char[] carr = new char[len];
        for (int i = 0; i < len; i++) {
            carr[i] = c;
        }
        return new String(carr);
    }

    /**
     * 去除尾部空格
     * 
     * @param str
     *            对象字符串
     * @return 去除空格后的字符串
     */
    public static String rtrim(String str) {

        assertNotNull(str);

        for (int i = str.length() - 1; i >= 0; i--) {
            if (HALF_SPACE != str.charAt(i) && FULL_SPACE != str.charAt(i)) {
                return str.substring(0, i + 1);
            }
        }
        return "";
    }

    /**
     * 去除尾部重复字符
     * 
     * @param str
     *            对象字符串
     * @param c
     *            重复字符
     * @return 去除重复字符后的字符串
     */
    public static String rtrim(String str, char c) {

        assertNotNull(str);

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != c) {
                return str.substring(0, i + 1);
            }
        }
        return "";
    }

    /**
     * 转义SQL字符串
     * 
     * @param str
     *            SQL字符串
     * @return 转义后的SQL字符串
     */
    public static String sqlEscape(String str) {

        assertNotNull(str);

        return str.replace(PUNC_BRACKET, ESCAPE_BRACKET)
                .replace(PUNC_PERCENT, ESCAPE_PERCENT)
                .replace(PUNC_UNDERLINE, ESCAPE_UNDERLINE)
                .replace(PUNC_SINGLE_QUOTE, ESCAPE_SINGLE_QUOTE);
    }

    /**
     * 逆转义SQL字符串
     * 
     * @param str
     *            SQL字符串
     * @return 逆转义后的SQL字符串
     */
    public static String sqlUnescape(String str) {

        assertNotNull(str);

        return str.replace(ESCAPE_SINGLE_QUOTE, PUNC_SINGLE_QUOTE)
                .replace(ESCAPE_UNDERLINE, PUNC_UNDERLINE)
                .replace(ESCAPE_PERCENT, PUNC_PERCENT)
                .replace(ESCAPE_BRACKET, PUNC_BRACKET);
    }

    /**
     * 去除头尾空格
     * 
     * @param str
     *            对象字符串
     * @return 去除空格后的字符串
     */
    public static String trim(String str) {
        return ltrim(rtrim(str));
    }

    /**
     * 去除头尾重复字符
     * 
     * @param str
     *            对象字符串
     * @param c
     *            重复字符
     * @return 去除重复字符后的字符串
     */
    public static String trim(String str, char c) {
        return rtrim(ltrim(str, c), c);
    }

    /**
     * 将下划线连接的字符串转换为驼峰表示的字符串
     * 
     * @param obj
     *            字符串变量
     * @return 驼峰表示的字符串
     */
    public static String underlineToCamel(String obj) {

        assertNotNull(obj);

        // 获取按下划线分割的子字符串组
        String[] subStrings = obj.split(PUNC_UNDERLINE);

        // 返回变量
        String retval = "";

        // 格式化为驼峰模式并连接每个子字符串
        for (String subString : subStrings) {
            retval += firstLetterFormat(subString.toLowerCase(),
                    StringFormatConstants.UPPER_CASE);
        }

        return retval;
    }

    /**
     * 全角英数字转半角
     * 
     * @param input
     *            字符串变量
     * @return 半角字符串
     */
    public static String replaceWideChars(String input) {
        // バッファの初期化
        StringBuffer output = new StringBuffer(input.length());
        for (int i = 0; i < input.length(); i++) {
            int idx = WIDECHARS.indexOf(input.charAt(i));
            if (idx != -1) {
                output.append(CHARS.charAt(idx));
            } else {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    /**
     * 半角英数字转全角
     * 
     * @param input
     *            字符串变量
     * @return 半角字符串
     */
    public static String replaceHalfChars(String input) {
        // バッファの初期化
        StringBuffer output = new StringBuffer(input.length());
        for (int i = 0; i < input.length(); i++) {
            int idx = CHARS.indexOf(input.charAt(i));
            if (idx != -1) {
                output.append(WIDECHARS.charAt(idx));
            } else {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    /**
     * 移除前后百分号
     * 
     * @param input
     *            字符串变量
     * @return 字符串
     */
    public static String removePercent(String input) {
        // バッファの初期化
        String strObj = null;
        if (!StringUtil.isEmpty(input) && input.startsWith(PUNC_PERCENT)
                && input.endsWith(PUNC_PERCENT)) {
            strObj = input.substring(1);
            strObj = strObj.substring(0, strObj.length() - 1);
        }
        return strObj;
    }

    /**
     * Null 转换
     * 
     * @param str
     *            字符
     * @return 字符
     */
    public static String nullToSp(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        return str;
    }

    /**
     * 去掉\r （画面上\r进入数据库时候会被去掉，所以比较与数据库值是否一致需要去掉画面上的\r）
     * 
     * @param str
     *            字符
     * @return 字符
     */
    public static String rtrimNewLine(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return rtrim(str).replace(NEW_LINE, "");
    }

    /**
     * 去掉\r\n 去掉回车(把\n替换成空格，避免两个英文单词连成一起)
     * 
     * @param str
     *            字符
     * @return 字符
     */
    public static String rtrimEnterNewLine(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return rtrim(str).replace(NEW_LINE, "").replace(NEXT_LINE, " ");
    }

    /**
     * 判断字符串是否是数字
     * 
     * @param str
     *            字符
     * @return 判断结果
     */
    public static boolean isNumeirc(String str) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        return pattern.matcher(str).matches();
    }

    /**
     * Null值判断
     * 
     * @param obj
     *            对象
     */
    private static void assertNotNull(Object obj) {
        if (null == obj) {
            throw new InvalidParameterException();
        }
    }

    /**
     * 负值判断
     * 
     * @param num
     *            数字
     */
    private static void assertNotNegative(int num) {
        if (0 > num) {
            throw new InvalidParameterException();
        }
    }

    /**
     * Nullの項目を変換する
     * 
     * @param <T>
     *            項目タイプ
     * @param oldValue
     *            元の値
     * @param newValue
     *            新しい値
     * @return 元の値がNullの場合、新しい値を返す
     */
    public static <T> T denull(T oldValue, T newValue) {
        if (oldValue == null) {
            return newValue;
        } else {
            return oldValue;
        }
    }

    /**
     * 将数字转换成利率
     * 
     * @param num
     *            数值
     * @return 转换值
     */
    public static String numberToRate(BigDecimal num) {
        if (num == null) {
            return "";
        }
        String numStr = StringUtil.formatNumber(num,
                StringFormatType.NUMBER_FORMAT_FIVE_NUMBER);
        DecimalFormat df = new DecimalFormat(
                StringFormatType.NUMBER_FORMAT_NOLMAL_RATE.format());
        return df.format(new BigDecimal(numStr))
                + CommonConstants.MESSAGE_PARAM_INDUCTOR.value();
    }

}
