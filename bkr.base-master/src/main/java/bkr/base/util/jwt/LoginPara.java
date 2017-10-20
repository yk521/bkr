package bkr.base.util.jwt;

import lombok.Data;

@Data
public class LoginPara {

	private String clientId;  //userId
    private String userName;  //name
    private String password;  //password
    private String captchaCode;  //mail
    private String captchaValue;  //photo
}
