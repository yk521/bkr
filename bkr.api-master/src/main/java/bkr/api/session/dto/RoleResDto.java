package bkr.api.session.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoleResDto {

	 /** 角色Id */
    private Long roleId;

    /** 角色名 */
    private String name;

    /** 说明 */
    private String memo;
    
    /** 权限 */
    private List<PermissionDto> permissions;
    
}
