package bkr.base.api.result;

public enum Message {
	
	/*public static String Other = "other";
	public static String NameIsNull = "nameIsNull";
	public static String PasswordIsNull = "passwordIsNull";
	public static String MailIsNull = "mailIsNull";
	public static String PhotoIsNull = "photoIsNull";
	public static String NameOrPassIsWrong = "nameOrPassIsWrong";
	public static String LoginSuccess = "loginSuccess";
	public static String LogoutSuccess = "logoutSuccess";
	public static String AddSuccess = "addSuccess";
	public static String DeleteSuccess = "deleteSuccess";
	public static String ModifySuccess = "modifySuccess";
	public static String UserList = "userList";
	public static String UserDetail = "userDetail";
	public static String Success = "success";
	public static String Failure = "failure";
	public static String FileIsEmpty = "fileIsEmpty";
	public static String UploadSuccess = "uploadSuccess";
	public static String UploadFilure = "uploadFilure";*/
	
	Other("other"),
	NameIsNull("nameIsNull"),
	PasswordIsNull("passwordIsNull"),
	MailIsNull("mailIsNull"),
	PhotoIsNull("photoIsNull"),
	NameOrPassIsWrong("nameOrPassIsWrong"),
	LoginSuccess("loginSuccess"),
	LogoutSuccess("logoutSuccess"),
	AddSuccess("addSuccess"),
	DeleteSuccess("deleteSuccess"),
	ModifySuccess("modifySuccess"),
	UserList("userList"),
	UserDetail("userDetail"),
	Success("success"),
	Failure("failure"),
	FileIsEmpty("fileIsEmpty"),
	UploadSuccess("uploadSuccess"),
	UploadFilure("uploadFilure");
	
	private String value;
	
	 /**
     * 构造函数
     * 
     * @param value
     *            参数值
     */
	Message(String value)
	{
		this.value = value;
	}
	
	/**
     * 返回参数值
     * 
     * @return 参数值
     */
	public String value() {
        return value;
    }
}
