package bkr.api.session.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bkr.api.session.dto.UserReqDto;
import bkr.api.session.dto.UserResDto;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.string.StringUtil;
import bkr.core.user.entity.User;
import bkr.core.user.service.UserService;

/**
 * 用户控制器
 * 
 * @author yk
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	/** 用户服务接口 */
	@Autowired
	private UserService userService;

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
	JsonResult<UserResDto> login(@RequestBody UserReqDto userReqDto) {
		// 入力验证
		// 用户名
		if (StringUtil.isEmpty(userReqDto.getName())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		}

		// 密码
		if (StringUtil.isEmpty(userReqDto.getPassword())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.PasswordIsNull));
		}

		// 根据用户名和密码取得注册用户
		User user = userService.login(userReqDto.getName(), userReqDto.getPassword());

		// 用户不存在的长
		if (user == null) {
			return new JsonResult<UserResDto>(ResultCode.FAILURE, messageHelper.getMessage(Message.NameOrPassIsWrong));
		}

		// Controller的入出力原则上使用DTO
		ModelMapper modelMapper = new ModelMapper();
		UserResDto dto = modelMapper.map(user, UserResDto.class);
		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.LoginSuccess), dto);
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/session/logout", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<UserResDto> logout() {
		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.LogoutSuccess));
	}

	/**
	 * 用户集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<Map<String, Object>> userPage(@RequestBody UserReqDto userReqDto) {
		User u = new User();
		u.setName(userReqDto.getName());
		u.setMail(userReqDto.getMail());

		// 取得用户集合
		Page<User> userPage = userService.userPage(u, userReqDto.getPage(), userReqDto.getCount());
		Map<String, Object> map = new HashMap<String, Object>();
		if (userPage.hasContent()){
			List<User> userList = userPage.getContent();
			List<UserResDto> dtoList = new ArrayList<UserResDto>();
			for (User user : userList) {
				ModelMapper modelMapper = new ModelMapper();
				UserResDto dto = modelMapper.map(user, UserResDto.class);
				dtoList.add(dto);
			}
			userPage.getTotalElements();
			
			map.put("list", dtoList);
			map.put("count", userPage.getTotalElements());
	
			return new JsonResult<Map<String, Object>>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserList), map);
		}
		return new JsonResult<Map<String, Object>>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserList), map);
	}

	/**
	 * 添加用户
	 * 
	 * @param createDto
	 * @return
	 */
	@RequestMapping(value = "/user/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<UserResDto> addUser(@RequestBody UserReqDto userReqDto) {

		// 用户名
		if (StringUtil.isEmpty(userReqDto.getName())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		}

		// 密码
		if (StringUtil.isEmpty(userReqDto.getPassword())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.PasswordIsNull));
		}

		// 邮箱
		if (StringUtil.isEmpty(userReqDto.getMail())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.MailIsNull));
		}

		// 头像
		/**
		 * if (StringUtil.isEmpty(createDto.getPhoto())) { return new
		 * JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, "头像不能为空！"); }
		 */

		User user = new User();
		user.setName(userReqDto.getName());
		user.setPassword(userReqDto.getPassword());
		user.setMail(userReqDto.getMail());
		user.setPhoto(userReqDto.getPhoto());

		// 添加用户
		userService.createUser(user);

		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.AddSuccess));
	}

	/**
	 * 用户详情
	 * 
	 * @param detailDto
	 * @return
	 */
	@RequestMapping(value = "/user/detail", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<UserResDto> userDetail(@RequestBody UserReqDto userReqDto) {
		User user = userService.userDetail(userReqDto.getId());
		ModelMapper modelMapper = new ModelMapper();
		UserResDto dto = modelMapper.map(user, UserResDto.class);
		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserDetail), dto);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param modifyDto
	 * @return
	 */
	@RequestMapping(value = "/user/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<UserResDto> modifyUser(@RequestBody UserReqDto userReqDto) {
		// 用户名
		if (StringUtil.isEmpty(userReqDto.getName())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		}

		// 密码
		/*
		 * if (StringUtil.isEmpty(modifyDto.getPassword())) { return new
		 * JsonResult<UserResDto>(ResultCode.PARAMS_ERROR,
		 * Message.PasswordIsNull); }
		 */

		// 邮箱
		if (StringUtil.isEmpty(userReqDto.getMail())) {
			return new JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.MailIsNull));
		}

		// 头像
		/*
		 * if (StringUtil.isEmpty(modifyDto.getPhoto())) { return new
		 * JsonResult<UserResDto>(ResultCode.PARAMS_ERROR, Message.PhotoIsNull);
		 * }
		 */

		User user = new User();
		user.setUserId(userReqDto.getUserId());
		user.setName(userReqDto.getName());
		user.setPassword(userReqDto.getPassword());
		user.setMail(userReqDto.getMail());
		user.setPhoto(userReqDto.getPhoto());
		userService.modifyUser(user);
		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.ModifySuccess));
	}

	/**
	 * 删除用户
	 * 
	 * @param deleteDto
	 * @return
	 */
	@RequestMapping(value = "/user/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<UserResDto> deleteUser(@RequestBody UserReqDto userReqDto) {
		userService.deleteUser(userReqDto.getUserId());
		return new JsonResult<UserResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.DeleteSuccess));
	}
}
