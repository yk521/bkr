package bkr.base.api.result;

/**
 * 返回结果code枚举类
 * 
 * @author chengd
 */
public enum ResultCode {
    /** 200:成功 */
    SUCCESS("200"),
    
    /** 300:失败 */
    FAILURE("300"),
    
    /** 400:没有登录 */
    NOT_LOGIN("400"),

    /** 401:发生异常 */
    EXCEPTION("401"),

    /** 402:系统错误 */
    SYS_ERROR("402"),

    /** 403:参数错误 */
    PARAMS_ERROR("403"),

    /** 410:不支持或已经废弃 */
    NOT_SUPPORTED("410"),

    /** 444:AuthCode错误 */
    INVALID_AUTHCODE("444"),

    /** 445:太频繁的调用 */
    TOO_FREQUENT("445"),

    /** 499:未知的错误 */
    UNKNOWN_ERROR("499");

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
    ResultCode(String value) {
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
