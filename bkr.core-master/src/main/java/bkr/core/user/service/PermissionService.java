package bkr.core.user.service;

import bkr.core.user.entity.Permission;

/**
 * 权限接口
 * 
 * @author yk
 *
 */
public interface PermissionService {

	/**
	 * 权限列表
	 *  
	 * @return
	 */
	Iterable<Permission> permissionList();
	
	/**
	 * 权限详情
	 * 
	 * @param permissionId
	 * @return
	 */
	Permission permissionDetail(Long permissionId);
	
	/**
	 * 更改权限信息
	 * 
	 * @param permission
	 */
	void modifyPermission(Permission permission);
	
	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	void deletePermission(Long permissionId);
	
	/**
	 * 添加权限
	 * 
	 * @param permission
	 */
	void createPermission(Permission permission);
}
