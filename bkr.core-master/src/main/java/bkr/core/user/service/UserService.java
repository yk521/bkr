package bkr.core.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import bkr.core.user.entity.User;

/**
 * 用户接口
 * 
 * @author yk
 * 
 */

public interface UserService {
	
	 /**
     * 登录
     * 
     * @param userName
     *            用户名
     * @param userPassword
     *            用户密码
     */
    User login(String userName, String userPassword);
	
	/** 添加用户 */
	void createUser(User user);
	
	/** 用户列表 */
	Page<User> userPage(User user, String page, String count);
	
	/** 用户详情 */
	User userDetail(Long userId);
	
	/** 修改用户 */
	void modifyUser(User user);
	
	/** 删除用户 */
	void deleteUser(Long userId);
	
}
