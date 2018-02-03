package com.wenhaofan.index;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wenhaofan.common.interceptor.LoginInterceptor;
import com.wenhaofan.common.model.Article;

/**
 * 网站首页控制器
 * 
 * @author fwh
 *
 */
@Clear(LoginInterceptor.class)
@Before(IndexSeoInterceptor.class)
public class IndexController extends Controller {

	IndexService service = IndexService.me;

	public void index() {
		Integer categoryId = getParaToInt("c");
		Integer pageNum=getParaToInt("p",1);
		if(pageNum<=0) {
			pageNum=1;
		}
		List<Article> topArticles = service.getTopArticle(categoryId);
		
		Page<Article> articlePage = service.pageArticle(categoryId,topArticles,pageNum,8);
		
		setAttr("topArticles", topArticles);
		setAttr("articlePage", articlePage);
		
		setSessionAttr("queryCategoryId", categoryId);
		render("index.html");
	}


	public void profiles() {
		render("profiles/profiles.html");
	}

}
