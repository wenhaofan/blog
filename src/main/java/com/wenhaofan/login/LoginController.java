package com.wenhaofan.login;

import com.jfinal.aop.Clear;
import com.jfinal.kit.Ret;
import com.wenhaofan.common.controller.BaseController;
import com.wenhaofan.common.interceptor.LoginInterceptor;
import com.wenhaofan.common.kit.IpKit;
/**
 * 后台页面控制器
 * @author fwh
 *
 */
@Clear(LoginInterceptor.class)
public class LoginController extends BaseController{
	private LoginService loginService=LoginService.me;
	

	public void index(){
		if(isLogin()) {
			render("/admin");
			return;
		}
		
		String account=getPara("ac");
		String pwd=getPara("pwd");
		String ip=IpKit.getRealIp(getRequest());
		
		boolean isKepp=getParaToBoolean("k",false);
		
		
		Ret ret=loginService.login(account,pwd,isKepp,ip);
		
		if(ret.isOk()) {
			String sessionId=ret.getStr(LoginService.sessionIdName);
			int maxAge=ret.getInt("cookieMaxAge");
			setAttr(LoginService.loginUserKey,ret.get(LoginService.loginUserKey));
			setCookie(LoginService.sessionIdName,sessionId, maxAge,true);
			redirect("/");
			return;
		}
		
		renderError(404);
		
	}

	
}

