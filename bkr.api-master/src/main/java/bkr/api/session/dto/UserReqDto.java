package bkr.api.session.dto;

import lombok.Data;

/**
 * 
 *用户请求 DTO
 *
 *@author yk
 */
@Data
public class UserReqDto {

	/** 用户ID */
	private Long userId;
	
	/** 用户名 */
    private String name;

    /** 密码 */
    private String password;
    
    /** 邮箱 */
    private String mail;
    
    /** 头像 */
    private String photo;
    
    /** 用户角色 */
    private RoleReqDto role;

    /** 接收ID */
    private Long id;
    
    /** 分页*/
    private String page;
    
    /** 每页数量 */
    private String count;
}
