package bkr.core.user.service;

import bkr.core.user.entity.User;

/**
 * 登录服务接口
 * 
 * @author chengd
 */
public interface LoginService {
    /**
     * 登录
     * 
     * @param userName
     *            用户名
     * @param userPassword
     *            用户密码
     */
    User login(String userName, String userPassword);
}
