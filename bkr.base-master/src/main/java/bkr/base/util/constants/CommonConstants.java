package bkr.base.util.constants;

/**
 * 通用常量枚举
 * 
 * @author chengd
 */
public enum CommonConstants {
    /**
     * 改ページフラグ：前一頁
     */
    PAGING_PRE("-1"),

    /**
     * 改ページフラグ：下一頁
     */
    PAGING_NXT("1"),

    /**
     * 改ページフラグ：GO
     */
    PAGING_GO("0"),

    /**
     * true(String型)
     */
    STRING_TRUE("true"),

    /**
     * false(String型)
     */
    STRING_FALSE("false"),

    /**
     * 1单引号
     */
    STR_QUOTATION_MARKS("'"),

    /**
     * 2双引号
     */
    STR_DOUBLE_MARKS("''"),
    /**
     * 消息参数引导
     */
    MESSAGE_PARAM_INDUCTOR("%"),

    /**
     * 参数开始
     */
    PARAM_BEGIN("["),

    /**
     * 参数结束
     */
    PARAM_END("]"),

    /**
     * 括弧开始
     */
    INCLUDE_BEGIN("("),

    /**
     * 括弧结束
     */
    INCLUDE_END(")"),

    /**
     * コンマ
     */
    COMMA(",");

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
    CommonConstants(String value) {
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
