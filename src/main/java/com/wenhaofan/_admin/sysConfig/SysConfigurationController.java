package com.wenhaofan._admin.sysConfig;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.wenhaofan._admin.user.AdminUserService;
import com.wenhaofan.common._config.BlogConfig;
import com.wenhaofan.common._config.BlogConstant;
import com.wenhaofan.common.model.User;

/**
 * 完成项目部署的控制器
 * 
 * @author fwh
 *
 */
@Clear()

public class SysConfigurationController extends Controller {

	private AdminUserService service = AdminUserService.me;

	public void index() {

		if (BlogConstant.IS_INIT) {
			renderError(404);
			return;
		}

		String user = getPara("user");
		String passWord = getPara("pwd");
		String jdbcUrl = getPara("jdbcUrl");
		
		if(StrKit.isBlank(user)||StrKit.isBlank(passWord)) {
			setAttr("step",1);
			render("/_view/back/init.html");
			return;
		}
		
		boolean isSuccess=false;
		String mes=null;
		int step=1;

		try {
			isSuccess=BlogConfig.createDb(jdbcUrl, user, passWord);
			if(isSuccess) {
				BlogConstant.IS_INIT = true;
				if (service.isExistAdmin()) {
					redirect("/");
					return;
				}
				
				step=2;
			}else {
				mes="账号密码错误!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess=false;
			step=1;
			mes="出bug了!";
			render("/_view/back/init.html");
			return;
		}
		
		setAttr("step", step);
		setAttr("mes", mes);
		render("/_view/back/init.html");
	}

	public void initAdminUser() {
		User user = getModel(User.class, "", true);
		user.setAge(18);
		user.setIsValid(BlogConstant.VALID_YES);
		user.setLevel(1);
		user.setName("admin");
		user.setSex(1);
		user.setUkPhone("admin");
		user.setUkEmail("admin@qq.com");
		service.saveUser(user);
		redirect("/admin");
	}
}
