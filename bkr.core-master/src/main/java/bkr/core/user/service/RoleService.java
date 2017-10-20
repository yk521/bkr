package bkr.core.user.service;

import org.springframework.data.domain.Page;

import bkr.core.user.entity.Role;

/**
 * 角色接口
 * 
 * @author yk
 *
 */
public interface RoleService {

	/**
	 * 角色列表
	 *  
	 * @return
	 */
	Page<Role> rolePage(String page, String count);
	
	/**
	 * 角色详情
	 * 
	 * @param roleId
	 * @return
	 */
	Role roleDetail(Long roleId);
	
	/**
	 * 更改角色信息
	 * 
	 * @param role
	 */
	void modifyRole(Role role);
	
	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	void deleteRole(Long roleId);
	
	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	void createRole(Role role);
}
