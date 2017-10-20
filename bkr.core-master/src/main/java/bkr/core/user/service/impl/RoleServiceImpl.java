package bkr.core.user.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import bkr.core.user.entity.QRole;
import bkr.core.user.entity.Role;
import bkr.core.user.repoistory.RoleRepository;
import bkr.core.user.repoistory.UserRepository;
import bkr.core.user.service.RoleService;

/**
 * 角色接口实现类
 * 
 * @author yk
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    
	/**
	 * 角色列表
	 *  
	 * @return
	 */
	@Override
	public Page<Role> rolePage(String page, String count) {
		Page<Role> rolePage = roleRepository.findAll(new PageRequest(Integer.parseInt(page)-1,
				Integer.parseInt(count), new Sort(Direction.ASC, "roleId")));
		return rolePage;
	}
	
	/**
	 * 角色详情
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public Role roleDetail(Long roleId) {
		Role role = roleRepository.findOne(roleId);
		return role;
	}

	/**
	 * 更改角色信息
	 * 
	 * @param role
	 */
	@Transactional
	@Override
	public void modifyRole(Role role) {
		/*QRole qrole = QRole.role;
		queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(qrole).set(qrole.name,role.getName())
		.set(qrole.memo, role.getMemo())
		.where(qrole.roleId.eq(role.getRoleId())).execute();*/
		
		Role r = roleRepository.findOne(role.getRoleId());
		if(r != null){
			roleRepository.save(role);
		}
	}
	
	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	@Transactional
	@Override
	public void deleteRole(Long roleId) {
		Role role = roleRepository.findOne(roleId);
		if(role != null) {
			//删除关联用户表
			userRepository.deleteByRole(role);
			roleRepository.delete(role);
		}
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	@Transactional
	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
	}

}
