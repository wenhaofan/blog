package com.wenhaofan.common.interceptor;

import java.util.List;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.model.Basic;
import com.wenhaofan.common.model.Blogger;
import com.wenhaofan.common.model.Blogroll;
import com.wenhaofan.common.model.Category;
import com.wenhaofan.common.service.IndexService;


public class IndexInterceptor implements Interceptor{

	IndexService service=IndexService.me;
	
	@Override
	public void intercept(Invocation inv) {
		String actionKey=inv.getViewPath();
		if(actionKey.indexOf("/_view/front/")!=-1) {
			
			Controller c=inv.getController();

			List<Article> hotArticles=service.listHotArticle();
			
			List<Category> categorys=service.listCategory();
			
			Blogger blogger=service.getBlogger();
			
			List<Blogroll> blogrolls=service.listBlogroll();
			
			Basic basic=service.getBasic();
			c.setAttr("blogrolls", blogrolls);
			c.setAttr("blogger", blogger);
			c.setAttr("categorys", categorys);
			c.setAttr("hotArticles",hotArticles);
			c.setAttr("basic", basic);
			inv.invoke();
			
		}else {
			inv.invoke();
		}
	}

}
