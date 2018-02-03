package com.wenhaofan.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.wenhaofan.common.model.User;
import com.wenhaofan.login.LoginService;

public class LoginInterceptor implements Interceptor{

	/**
	 * 后台权限验证,未登录则不能访问
	 */
	public void intercept(Invocation inv) {
		
		Controller c=inv.getController();
		
		String sessionId=c.getCookie(LoginService.sessionIdName);

		if(sessionId!=null) {
			//通过sessionId从缓存中获取登录用户
			User loginUser=LoginService.me.getUserWithSessionId(sessionId);
			//如果依然为空则从数据库中寻找有效的登录用户
			if(loginUser==null) {
				loginUser=LoginService.me.loginWithSessionId(sessionId);
			}
			
			if(loginUser!=null) {
				c.setAttr(LoginService.loginUserKey, loginUser);
				inv.invoke();
			}else {
				//为空则表示cookie无用，删之
				c.removeCookie(LoginService.sessionIdName);
				c.renderError(404);
			}
		}else {
			c.renderError(404);
		}
		
	}

	
}
