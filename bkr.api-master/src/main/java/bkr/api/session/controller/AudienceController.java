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

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AudienceController {

	@Autowired
	Audience audience;
	
	@Autowired
	private MessageHelper messageHelper;
	
	@RequestMapping(value = "/audience/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    JsonResult<Audience> login() {
		
		return new JsonResult<Audience>(ResultCode.SUCCESS, messageHelper.getMessage(Message.Success), audience);
	}
}
