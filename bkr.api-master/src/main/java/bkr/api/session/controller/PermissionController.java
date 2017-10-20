package bkr.api.session.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bkr.api.session.dto.PermissionDto;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.string.StringUtil;
import bkr.core.user.entity.Permission;
import bkr.core.user.service.PermissionService;

/**
 * 权限控制器
 * 
 * @author yk
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PermissionController {

	/** 权限服务接口 */
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private MessageHelper messageHelper;
	
	/**
	 * 权限列表
	 * 
	 * @return
	 */
	 @RequestMapping(value = "/permission/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	 @ResponseBody
	 public JsonResult<Map<String, List<PermissionDto>>> permissionList(){
		 
		 //权限集合
		 Iterable<Permission> permissionList = permissionService.permissionList();
		 
		 //转换为DTO集合
		 List<PermissionDto> dtoList = new ArrayList<PermissionDto>();
		 ModelMapper modelMapper = new ModelMapper();
		 for (Permission permission : permissionList) {
			 PermissionDto dto = modelMapper.map(permission, PermissionDto.class);
			 dtoList.add(dto);
		}
		 
		 //放入map中
		 Map<String, List<PermissionDto>> dtoMap = new HashMap<String, List<PermissionDto>>();
		 dtoMap.put("list", dtoList);
		 return new JsonResult<Map<String, List<PermissionDto>>>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserList), dtoMap);
	 }
	 
	 /**
	  * 权限详情
	  * 
	  * @param permissionDto
	  * @return
	  */
	 @RequestMapping(value = "/permission/detail", produces = { MediaType.APPLICATION_JSON_VALUE })
	 @ResponseBody
	 public JsonResult<PermissionDto> permissionDetail(@RequestBody PermissionDto permissionDto){
		 Permission permission = permissionService.permissionDetail(permissionDto.getPermissionId());
		 ModelMapper modelMapper = new ModelMapper();
		 PermissionDto dto = modelMapper.map(permission, PermissionDto.class);
		 return new JsonResult<PermissionDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserDetail), dto);
	 }
	 
	 /**
	  * 更改权限信息
	  * 
	  * @param permissionDto
	  * @return
	  */
	 @RequestMapping(value = "/permission/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	 @ResponseBody
	 public JsonResult<PermissionDto> modifyPermission(@RequestBody PermissionDto permissionDto){
		 Permission permission = new Permission();
		 permission.setPermissionId(permissionDto.getPermissionId());
		 permission.setName(permissionDto.getName());
		 permissionService.modifyPermission(permission);
		 return new JsonResult<PermissionDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.ModifySuccess));
	 }
	 
	 /**
	  * 删除权限
	  * 
	  * @param permissionDto
	  * @return
	  */
	 @RequestMapping(value = "/permission/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	 @ResponseBody
	 public JsonResult<PermissionDto> deletePermission(@RequestBody PermissionDto permissionDto){
		 permissionService.deletePermission(permissionDto.getPermissionId());
		 return new JsonResult<PermissionDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.DeleteSuccess));
	 }
	 
	 /**
	  * 添加权限
	  * 
	  * @param permissionDto
	  * @return
	  */
	 @RequestMapping(value = "/permission/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	 @ResponseBody
	 public JsonResult<PermissionDto> createPermission(@RequestBody PermissionDto permissionDto){
		 //角色名
		 if (StringUtil.isEmpty(permissionDto.getName())) {
			 return new JsonResult<PermissionDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		 }
		 Permission permission = new Permission();
		 permission.setName(permissionDto.getName());
		 permissionService.createPermission(permission);
		 return new JsonResult<PermissionDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.AddSuccess));
	 }
}
