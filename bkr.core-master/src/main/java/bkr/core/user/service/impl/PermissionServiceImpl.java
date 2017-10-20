package bkr.core.user.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

import bkr.core.user.entity.Permission;
import bkr.core.user.entity.QPermission;
import bkr.core.user.repoistory.PermissionRepository;
import bkr.core.user.service.PermissionService;

/**
 * 权限接口实现类
 * 
 * @author yk
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    
	/**
	 * 权限列表
	 *  
	 * @return
	 */
	@Override
	public Iterable<Permission> permissionList() {
		Iterable<Permission> permissionList = permissionRepository.findAll();
		return permissionList;
	}
	
	/**
	 * 权限详情
	 * 
	 * @param permissionId
	 * @return
	 */
	@Override
	public Permission permissionDetail(Long permissionId) {
		Permission permission = permissionRepository.findOne(permissionId); 
		return permission;
	}

	/**
	 * 更改权限信息
	 * 
	 * @param permission
	 */
	@Override
	public void modifyPermission(Permission permission) {
		QPermission qpermission = QPermission.permission;
		queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(qpermission)
		.set(qpermission.name, permission.getName())
		.where(qpermission.permissionId.eq(permission.getPermissionId())).execute();
	}

	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	@Override
	public void deletePermission(Long permissionId) {
		QPermission qpermission = QPermission.permission;
		queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.delete(qpermission).execute();
		
		//permissionRepository.delete(permissionId);
	}

	/**
	 * 添加权限
	 * 
	 * @param permission
	 */
	@Override
	public void createPermission(Permission permission) {
		permissionRepository.save(permission);
	}

}
