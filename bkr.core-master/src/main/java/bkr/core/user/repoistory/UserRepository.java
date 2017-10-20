package bkr.core.user.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bkr.core.user.entity.Role;
import bkr.core.user.entity.User;

/**
 * 用户表库
 * 
 * @author chengd
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        QueryDslPredicateExecutor<User> ,JpaSpecificationExecutor<User> {

	//@Query
	@Query("select u from User u where name=:username and password=:userpass")
	User query(@Param("username") String name,@Param("userpass") String pasword);
	
	//JPA方法名
	User findByNameAndPassword(String name, String password);
	
	@Query("select u from User u")
	List<User> userList();
	
	User findOne(Long userId);
	
	List<User> findAll();
	
	List<User> findByName(String name);
	
	List<User> findByMail(String mail);
	
	List<User> findByNameAndMail(String name, String mail);
	
	/** 级联删除时在RoleServiceImpl中使用 */
	void deleteByRole(Role role);
	
}
