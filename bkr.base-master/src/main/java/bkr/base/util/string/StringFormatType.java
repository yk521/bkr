package bkr.base.util.string;

/**
 * 字符串格式枚举
 * 
 * @author xuzhili
 */
public enum StringFormatType {
    /**
     * 空值
     */
    NULL("#,##0.00"),

    /**
     * 日期格式：2009年01月01日->2009/01/01 00:00:00
     */
    DATE_FORMAT_DATETIME2("yyyy/MM/dd HH:mm:ss"),

    /**
     * 日期格式：2009年01月01日->2009/01/01 00:00:00
     */
    DATE_FORMAT_DATETIME("dd-MMM-yyyy HH:mm:ss"),
    /**
     * 日期格式：2009年01月01日 00:00:00->00:00:00
     */
    DATE_FORMAT_TIME("HH:mm:ss"),
    /**
     * 日期格式：2009年01月01日->2009年01月01日
     */
    DATE_FORMAT_YEAR_MON_DAY("yyyy年MM月dd日"),

    /**
     * 日期格式：2009年01月01日->2009年01月01日
     */
    DATE_FORMAT_YEAR_MON_DAY_SINGLE("yyyy年M月d日"),

    /**
     * 日期格式：2009年01月01日->01月01日
     */
    DATE_FORMAT_MON_DAY("MM月dd日"),
    /**
     * 日期格式：2009年01月01日->09年01月
     */
    DATE_FORMAT_YEAR_MON("yy年MM月"),
    /**
     * 日期格式：2009年01月01日->2009年01月
     */
    DATE_FORMAT_FYEAR_MON("yyyy年MM月"),
    /**
     * 日期格式：2009年01月01日->09年01月
     */
    DATE_FORMAT_TYEAR_MON("yy年MM月"),
    /**
     * 日期格式：2009年01月01日->09/01/01
     */
    DATE_FORMAT_YY_MM_DD("yy/MM/dd"),
    /**
     * 日期格式：2009年01月02日->02/01/09
     */
    DATE_FORMAT_DD_MM_YY("dd/MM/yy"),

    /**
     * 日期格式：2009年01月01日->09/1/1
     */
    DATE_FORMAT_YY_M_D("yy/M/d"),

    /**
     * 日期格式：2009年01月01日->2009/01/01
     */
    DATE_FORMAT_YYYY_MM_DD("yyyy/MM/dd"),

    /**
     * 日期格式：2009年01月01日->2009/1/1
     */
    DATE_FORMAT_YYYY_M_D("yyyy/M/d"),

    /**
     * 日期格式：2009年01月01日->09/01/01
     */
    DATE_FORMAT_YY__MM__DD("yy-MM-dd"),

    /**
     * 日期格式：2009年01月01日->09/1/1
     */
    DATE_FORMAT_YY__M__D("yy-M-d"),

    /**
     * 日期格式：2009年01月01日->2009-01-01
     */
    DATE_FORMAT_YYYY__MM__DD("yyyy-MM-dd"),

    /**
     * 日期格式：2009年01月01日->2009/1/1
     */
    DATE_FORMAT_YYYY__M__D("yyyy-M-d"),

    /**
     * 日期格式：2009年01月01日->2009/01/01 00:00:00
     */
    DATE_FORMAT_YYYY__MM__DD__HH_MM_SS("yyyy/MM/dd HH:mm:ss"),

    /**
     * 日期格式：2009年01月01日->20090101000000
     */
    DATE_FORMAT_YYYY__MM__DD__HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * 日期格式：2009年01月01日->20090101
     */
    DATE_FORMAT_YYYYMMDD("yyyyMMdd"),

    /**
     * 日期格式：2009年01月01日->01-Jan-2009
     */
    DATE_FORMAT_DDMMMYYYY("dd-MMM-yyyy"),

    /**
     * 日期格式：2009年01月01日->01-Jan-09
     */
    DATE_FORMAT_DDMMMYY("dd-MMM-yy"),

    /**
     * 日期格式：2009年01月01日->200901
     */
    DATE_FORMAT_YYYYMM("yyyyMM"),

    /**
     * 日期格式：2009年01月01日->012009
     */
    DATE_FORMAT_MMYYYY("MMyyyy"),

    /**
     * 日期格式：2009年01月01日->20090101000000
     */
    DATE_FORMAT_YYYYMMDDHHMMSS("yyyyMMddHHmmss"),

    /**
     * 日期格式：2009年01月01日->20090101000000
     */
    DATE_FORMAT_YYYYMMDDHHMMSSS("yyyyMMddHHmmsss"),

    /**
     * 日期格式：2009年01月01日->200901010000000000
     */
    DATE_FORMAT_YYYYMMDDHHMMSSSSS("yyyyMMddHHmmssSSS"),

    /**
     * 日期格式：2009年01月->2009/01
     */
    DATE_FORMAT_YYYY_MM("yyyy/MM"),

    /**
     * 日期格式：2009年01月->Jan-2001
     */
    DATE_FORMAT_MMM_YYYY("MMM-yyyy"),

    /**
     * 日期格式：01月01日->01-Jan
     */
    DATE_FORMAT_DD_MMM("dd-MMM"),

    /**
     * 日期格式：2009年01月01日->2009/01/01 00:00
     */
    DATE_FORMAT_HH_MM("yyyy/MM/dd HH:mm"),

    /**
     * 日期格式：2009年01月01日->01/01
     */
    DATE_FORMAT_MM_DD("MM/dd"),

    /**
     * 日期格式：2009年01月/01日->1/1
     */
    DATE_FORMAT_M_D("M/d"),

    /**
     * 日期格式：2009年01月->0901
     */
    DATE_FORMAT_YYMM("yyMM"),

    /**
     * 日期格式：2009年01月02日->0102
     */
    DATE_FORMAT_MMDD("MMdd"),

    /**
     * 日期格式：2009年01月02日->02
     */
    DATE_FORMAT_DD("dd"),

    /**
     * 数字格式：1234567->1,234,567
     */
    NUMBER_FORMAT_INTEGER_NUMBER("#,##0"),

    /**
     * 金额格式：1234567.89->1,234,567.89
     */
    NUMBER_FORMAT_MONEY("#,##0.00"),

    /**
     * 金额格式：1234567.89->1,234,567.890
     */
    NUMBER_FORMAT_MONEY_THREE_DIGIT("#,##0.000"),

    /**
     * 金额格式：1234567.89->1,234,567
     */
    NUMBER_FORMAT_MONEY_JPY("#,##0"),

    /**
     * 金额格式：1234567.89->1,234,567.89000000000000
     */
    NUMBER_FORMAT_FOURTEEN_MONEY("#,##0.00############"),

    /**
     * 金额格式：1234567.89->1,234,567.##
     */
    NUMBER_FORMAT_TWO_MONEY("#,##0.##"),

    /**
     * 金额格式：1234567.89->1,234,567.89000000000
     */
    NUMBER_FORMAT_ELEVEN_MONEY("#,##0.00000000000"),

    /**
     * 金额格式：1234567.8->1,234,567.8
     */
    NUMBER_FORMAT_MONEY_NUMBER("#,##0.0"),

    /**
     * 数字格式：1234567.89->1234567.89
     */
    NUMBER_FORMAT_NUMBER("0.00"),

    /**
     * 数字格式：1234567.89->1234567.899
     */
    NUMBER_FORMAT_PER_NUMBER("0.000"),

    /**
     * 数字格式：0.0000
     */
    NUMBER_FORMAT_FOUR_NUMBER("0.0000"),

    /**
     * 数字格式：0.00000
     */
    NUMBER_FORMAT_FIVE_NUMBER("0.00000"),
    /**
     * 数字格式：0.000000
     */
    NUMBER_FORMAT_SIX_NUMBER("0.000000"),
    /**
     * 数字格式：0.0000000
     */
    NUMBER_FORMAT_SEVEN_NUMBER("0.0000000"),

    /**
     * 数字格式：0.00000000
     */
    NUMBER_FORMAT_EIGHT_NUMBER("0.00000000"),

    /**
     * 数字格式：#,##0.00000000000
     */
    NUMBER_FORMAT_ELEVEN_NUMBER("#,##0.###########"),

    /**
     * 数字格式：0.00000000000000
     */
    NUMBER_FORMAT_FOURTEEN_NUMBER("0.00############"),

    /**
     * 数字格式：0.00#############
     */
    NUMBER_FORMAT_FIFTEEN_NUMBER("0.00#############"),

    /**
     * 数字格式：1234567.89->1234567.89
     */
    NUMBER_FORMAT_NORMAL_NUMBER("0"),

    /**
     * 数字格式：9->09
     */
    NUMBER_FORMAT_00_NUMBER("00"),

    /**
     * 利率格式：#,###.00000
     */
    NUMBER_FORMAT_NOLMAL_RATE("#,###.00000");

    /**
     * 字符串格式
     */
    private String formatString;

    /**
     * 构造函数
     * 
     * @param formatString
     *            字符串格式
     */
    StringFormatType(String formatString) {
        this.formatString = formatString;
    }

    /**
     * 返回字符串格式
     * 
     * @return 字符串格式
     */
    public String format() {
        return formatString;
    }

}
