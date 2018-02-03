package com.wenhaofan.article;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.wenhaofan.common.interceptor.LoginInterceptor;
import com.wenhaofan.common.model.Article;
/**
 * 首页文章控制器
 * @author fwh
 *
 */
@Clear(LoginInterceptor.class)
@Before(ArticleSeoInterceptor.class)
public class ArticleController extends Controller{

	private ArticleService service=ArticleService.me;
	
	public void index(){
		Integer articleId=getParaToInt(0);
		
		
		Article article=service.getArticleById(articleId);
		
		if(article==null) {
			renderError(404);
			return;
		}
		
		List<Article> lastNextArticle=service.lastNextArticle(article);
		
		setAttr("lastNextArticle", lastNextArticle);
		setAttr("article",article);
		
		render("article_show.html");
	}
	

	/**
	 * 增加阅读数量,待完成
	 */
	public void addReadNum(){
		service.addReadNum(getParaToInt(0));;
		
		Ret json=new Ret().setOk();
		renderJson(json.toJson());
	}
	
	
}
