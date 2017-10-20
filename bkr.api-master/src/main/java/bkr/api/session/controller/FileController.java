package bkr.api.session.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bkr.api.session.dto.FileDto;
import bkr.base.api.Token.UpToken;
import bkr.base.api.component.MessageHelper;
import bkr.base.api.result.JsonResult;
import bkr.base.api.result.Message;
import bkr.base.api.result.ResultCode;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FileController {

	@Autowired
	private MessageHelper messageHelper;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping(value = "/file/getUploadToken", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public FileDto getUploadToken()
	{ 
		FileDto fileDto = new FileDto();
		fileDto.setUptoken(new UpToken().getUpToken());
		return fileDto;
	}
	//本地文件上传代码
	@RequestMapping(value = "/file/upload", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public JsonResult<String> upload(@RequestParam MultipartFile file)
	{ 
		if(file.isEmpty())
			return new JsonResult<String>(ResultCode.FAILURE, messageHelper.getMessage(Message.FileIsEmpty));//"文件为空";
		//获取文件名
		String fileName = file.getOriginalFilename();
		//System.out.println("文件大小为："+file.getSize());
		logger.info("上传的文件为：" + fileName);
		//获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		//文件上传后的路径
		String filePath = "E://upload//";
		// 解决中文问题，liunx下中文路径，图片显示问题
	    //fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		//检测是否存在目录
		if (!dest.getParentFile().exists())
			dest.getParentFile().mkdirs();
		try {
			file.transferTo(dest);
			return new JsonResult<String>(ResultCode.FAILURE, messageHelper.getMessage(Message.UploadSuccess), fileName);//"上传成功";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JsonResult<String>(ResultCode.FAILURE, messageHelper.getMessage(Message.UploadFilure));//"上传失败";
	}
}
