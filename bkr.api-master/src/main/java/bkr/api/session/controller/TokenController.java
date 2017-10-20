package bkr.api.session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bkr.Audience;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;
import bkr.base.util.jwt.AccessToken;
import bkr.base.util.jwt.JwtHelper;
import bkr.base.util.jwt.LoginPara;
import bkr.core.user.entity.User;
import bkr.core.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TokenController {

	@Autowired
	UserService userService;
	
	@Autowired
	Audience audience;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@RequestMapping(value = "/token/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
	public JsonResult<AccessToken> token(){
		
		LoginPara loginPara = new LoginPara();
		loginPara.setUserName("user1");
		loginPara.setPassword("111111");
		User user = userService.login(loginPara.getUserName(), loginPara.getPassword());
		
		if(user == null){
			return new JsonResult<AccessToken>(ResultCode.FAILURE, messageHelper.getMessage(Message.Failure));
		} 
		
		//拼装accessToken  
        String accessToken = JwtHelper.createJWT(user.getName(), user.getUserId(),  
                user.getUserId(), audience.getClientId(), audience.getName(),  
                audience.getExpiresSecond() * 1000, audience.getBase64Secret());
		
        AccessToken accessTokenEntity = new AccessToken();  
        accessTokenEntity.setAccess_token(accessToken);  
        accessTokenEntity.setExpires_in(audience.getExpiresSecond());  
        accessTokenEntity.setToken_type("bearer");
        
		return new JsonResult<AccessToken>(ResultCode.SUCCESS, messageHelper.getMessage(Message.Success), accessTokenEntity);
	}
}
