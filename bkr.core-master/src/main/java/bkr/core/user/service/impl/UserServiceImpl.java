package bkr.core.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import bkr.base.util.security.MD5Util;
import bkr.core.user.entity.QUser;
import bkr.core.user.entity.User;
import bkr.core.user.repoistory.UserRepository;
import bkr.core.user.service.UserService;

/** 用户接口实现类 
 * 
 * @author yk
 * */
@Service
public class UserServiceImpl implements UserService{

	/** 用户表库 */
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    
	/**
     * 登录
     * 
     * @param userName
     *            用户名
     * @param userPassword
     *            用户密码
     */
	@Override
	public User login(String userName, String userPassword) {
		// 用户表Q类
        QUser quser = QUser.user;

        // 密码加密
        String md5Pass = MD5Util.getMD5(userName + userPassword);
        
        // 检索用户
        // 静态Predicate查询
        //Predicate pred = UserPredicate.nameAndPassword(userName,md5Pass);
        //User user = userRepository.findOne(pred);
        
        // @Query查询
        //User user = userRepository.query(userName, md5Pass);
        
        // JPA方法名查询
        //User user = userRepository.findByNameAndPassword(userName, md5Pass);
        
        // 常规findone()查询
        User user = userRepository.findOne(quser.name.eq(userName).and(quser.password.eq(md5Pass)));
        
        // 返回检索结果
        return user;
	}
	
	/** 添加用户
	 * 
	 *  @param user 用户类  
	 */
	@Override
	public void createUser(User user) {
		user.setPassword(MD5Util.getMD5(user.getName() + user.getPassword()));
		userRepository.save(user);
	}

	/** 用户列表 */
	public Page<User> userPage(User user, String page, String count) {
		Page<User> userPage = userRepository.findAll(new Specification<User>(){
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(user.getName() != null && !user.getName().equals(""))
					predicates.add(cb.like(root.get("name"), "%"+user.getName()+"%"));
				if(user.getMail() != null && !user.getMail().equals(""))
					predicates.add(cb.like(root.get("mail"), "%"+user.getMail()+"%"));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(count), new Sort(Direction.ASC, "userId")));
		return userPage;
	}

	/** 用户详情 */
	@Override
	public User userDetail(Long userId){
		User user = userRepository.findOne(userId);
		if(user != null) return user;
		else return null;
	}
	
	/**
	 * 修改用户 
	 * 
	 * @Transactional  添加事务
	 */
	@Transactional
	@Override
	public void modifyUser(User user){
		/*QUser quser = QUser.user;
		queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(quser).set(quser.name,user.getName())
		.set(quser.mail, user.getMail())
		.where(quser.userId.eq(user.getUserId())).execute();*/
		
		User u = userRepository.findOne(user.getUserId());
		if (u != null) {
			u.setName(user.getName());
			u.setMail(user.getMail());
			userRepository.save(u);
		}
	
	}

	/** 删除用户 */
	@Transactional
	@Override
	public void deleteUser(Long userId) {
		QUser quser = QUser.user;
		User u = userRepository.findOne(userId);
		if (u != null) {
			queryFactory = new JPAQueryFactory(entityManager);
			queryFactory.delete(quser).where(quser.userId.eq(userId)).execute();
		}
		//jpa
		/*User u = userRepository.findOne(userId);
		if (u != null)
		userRepository.delete(userId);*/
	}

}
