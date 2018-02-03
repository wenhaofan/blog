package com.wenhaofan.common.controller;

import com.jfinal.core.Controller;
import com.wenhaofan.common.model.User;
import com.wenhaofan.login.LoginService;

public class BaseController extends Controller{
	
	private User loginUser=null;
	
	public User getLogUser() {
		if(loginUser==null) {
			loginUser=getAttr(LoginService.loginUserKey);
		}
		return loginUser;
	}
	
	public boolean isLogin() {
		return getLogUser()!=null;
	}
	
	public boolean notLogin() {
		return !isLogin();
	}
}
