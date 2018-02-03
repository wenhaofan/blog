package com.wenhaofan.common._config.back;

import com.jfinal.config.Routes;
import com.wenhaofan._admin.article.ArticleAdminController;
import com.wenhaofan._admin.basic.BasicController;
import com.wenhaofan._admin.blogger.BloggerController;
import com.wenhaofan._admin.blogroll.BlogrollController;
import com.wenhaofan._admin.category.CategoryController;
import com.wenhaofan._admin.diy.action.DiyActionAdminController;
import com.wenhaofan._admin.diy.assets.AssetsAdminController;
import com.wenhaofan._admin.diy.html.DiyAdminController;
import com.wenhaofan._admin.index.IndexAdminController;
import com.wenhaofan._admin.sysConfig.SysConfigurationController;
import com.wenhaofan._admin.user.AdminUserController;
import com.wenhaofan.common.uplod.FileUploadController;
import com.wenhaofan.login.LoginController;

/**
 * 后端路由配置
 * @author fwh
 *
 */
public class BackRoutes extends Routes {
	
	@Override
	public void config() { 
	   setBaseViewPath("/_view/back");
	   add("/admin",IndexAdminController.class,"/");
	   add("/login",LoginController.class,"/");
	   add("/admin/user",AdminUserController.class,"/");
	   add("/admin/article",ArticleAdminController.class,"/article/");
	   add("/admin/category",CategoryController.class,"/category/");
	   add("/admin/blogroll", BlogrollController.class,"/blogroll/");
	   add("/admin/common/upload",FileUploadController.class,"/");
	   add("/admin/basic",BasicController.class,"/basic/");
	   add("/admin/fisrt/init",SysConfigurationController.class,"/");
	   add("/admin/bloger",BloggerController.class,"/basic/");
	   add("/admin/diy/html",DiyAdminController.class,"/diy/");
	   add("/admin/diy/action", DiyActionAdminController.class,"/diy/");
	   add("/admin/diy/assets", AssetsAdminController.class,"/diy/");
	   //H:\Eclipse\eclipseWork\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\blog\_view\back\diy
	}

}
