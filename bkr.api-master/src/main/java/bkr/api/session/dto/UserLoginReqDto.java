package bkr.api.session.dto;

import lombok.Data;

/**
 * 用户登录入力DTO
 * 
 * @author chengd
 */
@Data
public class UserLoginReqDto {

    /** 用户名 */
    private String name;

    /** 密码 */
    private String password;
}
