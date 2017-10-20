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

import bkr.api.session.dto.RoleReqDto;
import bkr.api.session.dto.RoleResDto;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.string.StringUtil;
import bkr.core.user.entity.Role;
import bkr.core.user.service.RoleService;


/**
 * 角色控制器
 * 
 * @author yk
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MessageHelper messageHelper;
	
	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<Map<String, Object>> roleList(@RequestBody RoleReqDto reqDto){
		Page<Role> rolePage = roleService.rolePage(reqDto.getPage(), reqDto.getCount());
		List<RoleResDto> dtoList = new ArrayList<RoleResDto>();
		if(rolePage.hasContent()){
			for (Role role : rolePage.getContent()) {
				ModelMapper modelMapper = new ModelMapper();
				RoleResDto dto = modelMapper.map(role, RoleResDto.class);
				dtoList.add(dto);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", dtoList);
		map.put("count", rolePage.getTotalElements());
		return new JsonResult<Map<String, Object>>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserList), map);
	}
	
	/**
	 * 角色详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role/detail", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<RoleResDto> roleDetail(@RequestBody RoleReqDto roleReqDto){
		Role role = roleService.roleDetail(roleReqDto.getId());
		ModelMapper modelMapper = new ModelMapper();
		RoleResDto dto = modelMapper.map(role, RoleResDto.class);
		return new JsonResult<RoleResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.UserDetail), dto);
	}
	
	/**
	 * 更改角色信息
	 * 
	 * @param roleReqDto
	 * @return
	 */
	@RequestMapping(value = "/role/update", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<RoleResDto> modifyRole(@RequestBody RoleReqDto roleReqDto){
		Role role = new Role();
		role.setRoleId(roleReqDto.getRoleId());
		role.setName(roleReqDto.getName());
		role.setMemo(roleReqDto.getMemo());
		roleService.modifyRole(role);
		return new JsonResult<RoleResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.ModifySuccess));
	}
	
	/**
	 * 删除角色
	 * 
	 * @param roleReqDto
	 * @return
	 */
	@RequestMapping(value = "/role/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<RoleResDto> deleteRole(@RequestBody RoleReqDto roleReqDto){
		System.out.println(roleReqDto.getRoleId());
		roleService.deleteRole(roleReqDto.getRoleId());
		return new JsonResult<RoleResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.DeleteSuccess));
	}
	
	/**
	 * 添加角色
	 * 
	 * @param roleReqDto
	 * @return
	 */
	@RequestMapping(value = "/role/create", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public JsonResult<RoleResDto> addRole(@RequestBody RoleReqDto roleReqDto){
		
		//System.out.println(roleReqDto.toString());
		//角色名
		if (StringUtil.isEmpty(roleReqDto.getName())) {
			return new JsonResult<RoleResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		}
		//角色说明
		if (StringUtil.isEmpty(roleReqDto.getMemo())) {
			return new JsonResult<RoleResDto>(ResultCode.PARAMS_ERROR, messageHelper.getMessage(Message.NameIsNull));
		}
		
		Role role = new Role();
		role.setName(roleReqDto.getName());
		role.setMemo(roleReqDto.getMemo());
		roleService.createRole(role);
		return new JsonResult<RoleResDto>(ResultCode.SUCCESS, messageHelper.getMessage(Message.AddSuccess));
	}
}
