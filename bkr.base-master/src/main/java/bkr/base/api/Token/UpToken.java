package bkr.base.api.Token;

import com.qiniu.util.*;

public class UpToken {

	private static String AccessKey = "umVq3qPSQAUy-hNnpteESlaOSvba4kL8dubrCBFl";
	private static String SecretKey = "tyN8_RW_u_oWGd3o96e_gSXIqqZF9lJlfcp8vNU_";
	private static String Bucket = "yangk";
	
	public String getUpToken() {
		Auth auth = Auth.create(AccessKey, SecretKey);
	    String upToken = auth.uploadToken(Bucket);
		return upToken;
	}
	
}
