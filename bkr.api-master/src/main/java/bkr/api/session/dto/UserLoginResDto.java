package bkr.api.session.dto;

import java.util.List;

import lombok.Data;

/**
 * 用户登录结果DTO
 * 
 * @author chengd
 */
@Data
public class UserLoginResDto {
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

    /** 用户权限 */
    private List<PermissionDto> permissionList;
}
