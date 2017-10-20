package bkr.api.session.dto;

import java.util.List;

import bkr.core.user.entity.Permission;
import lombok.Data;

/**
 * 角色请求dto
 * 
 * @author chengd
 */
@Data
public class RoleReqDto {
    /** 角色Id */
    private Long roleId;

    /** 角色名 */
    private String name;

    /** 说明 */
    private String memo;
    
    /** 接收ID */
    private Long id;
    
    /** 分页*/
    private String page;
    
    /** 每页数量 */
    private String count;
    
    /** 权限 */
    private List<PermissionDto> permissions;
}
