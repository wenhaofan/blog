package com.wenhaofan.common._config.front;

import com.jfinal.config.Routes;
import com.wenhaofan.article.ArticleController;
import com.wenhaofan.index.IndexController;

/**
 * 前端路由配置
 * @author fwh
 *
 */
public class FrontRoutes extends Routes{

	@Override
	public void config() {
		setBaseViewPath("/_view/front/");
		add("/",IndexController.class);
		add("/article",ArticleController.class,"/article/");
	}

}
