package bkr.core.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bkr.base.util.security.MD5Util;
import bkr.core.user.entity.QUser;
import bkr.core.user.entity.User;
import bkr.core.user.repoistory.UserRepository;
import bkr.core.user.service.LoginService;

/**
 * 登录服务接口实现类
 * 
 * @author chengd
 */
@Service
public class LoginServiceImpl implements LoginService {

    /** 用户表库 */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 登录
     * 
     * @param userName
     *            用户名
     * @param userPassword
     *            用户密码
     */
    public User login(String userName, String userPassword) {
    	// 用户表Q类
        QUser quser = QUser.user;

        // 密码加密
        String md5Pass = MD5Util.getMD5(userPassword);
        
        // 检索用户
        User user = userRepository.findOne(quser.name.eq(userName).and(quser.password.eq(md5Pass)));
        // 返回检索结果
        return user;
    }
}
