package bkr.base.util.constants;

/**
 * UI共通Constants
 * 
 * @author chengd
 */
public enum StringFormatConstants {

    /**
     * 小写
     */
    LOWER_CASE("LOWER_CASE"),

    /**
     * 大写
     */
    UPPER_CASE("UPPER_CASE");

    /**
     * 参数值
     */
    private String value;

    /**
     * 构造函数
     * 
     * @param value
     *            参数值
     */
    StringFormatConstants(String value) {
        this.value = value;
    }

    /**
     * 返回参数值
     * 
     * @return 参数值
     */
    public String value() {
        return value;
    }
}
