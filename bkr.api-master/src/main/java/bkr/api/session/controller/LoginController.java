package bkr.api.session.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bkr.api.session.dto.UserLoginReqDto;
import bkr.api.session.dto.UserLoginResDto;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.string.StringUtil;
import bkr.core.user.entity.User;
import bkr.core.user.service.LoginService;

/**
 * 登录登出控制器
 * 
 * @author chengd
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/app")
public class LoginController {

    /** 登录服务 */
    @Autowired
    private LoginService loginService;

    @Autowired
	private MessageHelper messageHelper;
    /**
     * 登录
     * 
     * @param response
     *            ：用于保存token到cookie中
     * @param map
     *            ：包含userName和password
     * @return
     */
    
    @RequestMapping(value = "/session/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    JsonResult<UserLoginResDto> login(@RequestBody UserLoginReqDto loginReqDto) {
    
    	// 入力验证
        // 用户名
        if (StringUtil.isEmpty(loginReqDto.getName())) {
            return new JsonResult<UserLoginResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
        }

        // 密码
        if (StringUtil.isEmpty(loginReqDto.getPassword())) {
            return new JsonResult<UserLoginResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.PasswordIsNull));
        }

        // 根据用户名和密码取得注册用户
        User user = loginService.login(loginReqDto.getName(),
                loginReqDto.getPassword());

        // 用户不存在的长
        if (user == null) {
            return new JsonResult<UserLoginResDto>(ResultCode.FAILURE, messageHelper.getMessage(Message.NameOrPassIsWrong));
        }

        // Controller的入出力原则上使用DTO
        ModelMapper modelMapper = new ModelMapper();
        UserLoginResDto dto = modelMapper.map(user, UserLoginResDto.class);
        return new JsonResult<UserLoginResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.LoginSuccess), dto);
    }
}
