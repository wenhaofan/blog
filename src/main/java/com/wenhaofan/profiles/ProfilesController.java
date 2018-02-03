package com.wenhaofan.profiles;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.wenhaofan.common.interceptor.LoginInterceptor;
/**
 * 个人信息首页
 * @author fwh
 *
 */
@Clear(LoginInterceptor.class)
public class ProfilesController extends Controller {

	public void index(){
		render("/WEB-INF/front/profiles/profiles.html");
	}
}
