package com.wenhaofan.article;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.service.CategoryService;


public class ArticleService {
	public static final ArticleService me=new ArticleService();
	private Article dao=new Article().dao();
	private CategoryService categoryService=CategoryService.me;
	
	public Article getArticleById(Integer pkId) {
		Article article= dao.findById(pkId);
		if(article==null) {
			return article;
		}
		categoryService.setCategory(article);
		return article;
	}
	
	/**
	 * 获取一篇文章的上一篇和下一篇
	 * @param article
	 * @return
	 */
	public List<Article> lastNextArticle(Article article){
		SqlPara sql=dao.getSqlPara("article.lastNextArticle", article.getPkId());
		List<Article> articles= dao.find(sql);
		
		if(articles==null||articles.isEmpty()) {
			articles.add(article);
			articles.add(article);
		}else if (articles.size()==1) {
			articles.add(articles.get(0));
		}
		
		return articles;
	}
	
	
	public void addReadNum(Integer pkId){
		Db.update("update article set readNum=readNum+1 where pkId="+pkId);
	}

}
