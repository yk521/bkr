package bkr.api.session.dto;

import lombok.Data;

/**
 * 用户响应  DTO
 * 
 * @author yk
 *
 */
@Data
public class UserResDto {

	/** 用户Id */
    private Long userId;

    /** 用户名 */
    private String name;

    /** 邮箱 */
    private String mail;

    /** 头像 */
    private String photo;

    /** 用户角色 */
    private RoleReqDto role;
    
}
