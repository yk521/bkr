package bkr.base.api.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果对象格式
 * 
 * @author chengd
 */
public class JsonResult<T> {

	
    /** code值 */
    @Getter
    private String code;

    /** 消息 */
    @Setter
    @Getter
    private String message;

    /** 内容 */
    @Setter
    @Getter
    private T data;

    /**
     * 构造函数
     * 
     * @return void
     */
    public JsonResult() {
        this.setCode(ResultCode.SUCCESS);
    }

    /**
     * 构造函数
     * 
     * @param code
     *            code枚举对象
     * @param message
     *            消息文本
     * @return void
     */
    public JsonResult(T data) {
        this.setCode(ResultCode.SUCCESS);
        this.setData(data);
    }

    /**
     * 构造函数
     * 
     * @param code
     *            code枚举对象
     * @param message
     *            消息文本
     * @return void
     */
    public JsonResult(ResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * 构造函数
     * 
     * @param code
     *            code枚举对象
     * @param message
     *            消息文本
     * @param data
     *            业务数据
     * @return void
     */
    public JsonResult(ResultCode code, String message, T data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    /**
     * 设定code枚举对象
     * 
     * @param code
     *            code枚举对象
     */
    public void setCode(ResultCode code) {
        this.code = code.value();
    }

}
