package bkr.api.session.dto;

import lombok.Data;


/**
 * 权限表dto
 * 
 * @author chengd
 */
@Data
public class PermissionDto {
    /** 权限Id */
    private Long permissionId;

    /** 权限名 */
    private String name;
    
    /** 权限名 */
    private String text;
}
