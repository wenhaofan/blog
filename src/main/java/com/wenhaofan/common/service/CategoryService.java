package com.wenhaofan.common.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.model.Category;

public class CategoryService {

	private final Category dao=new Category().dao();
	
	public static final CategoryService me=new CategoryService();
	
	/**
	 * 为每个article对象初始化分类集合
	 * @param articles
	 */
	public void setCategorys(List<Article> articles) {
		Article tempArticle=null;
		Integer tempArticleId=null;
		List<Category> categorys=null;
		for(int i=0,size=articles.size();i<size;i++) {
			tempArticle=articles.get(i);
			tempArticleId=tempArticle.getPkId();
			categorys=dao.find("select * from category left join article_category on category.pkId=article_category.categoryId where article_category.articleId=?"
					,tempArticleId);
			tempArticle.setCategorys(categorys);
		}
	}
	
	public void setCategorys(Page<Article> page) {
		List<Article> articles=page.getList();
		setCategorys(articles);
	}
	
	public Article setCategory(Article article) {
		Integer tempArticleId=article.getPkId();
		SqlPara sql=dao.getSqlPara("share.listCategoryByArticleId", tempArticleId);
		List<Category> categorys=dao.find(sql);
		article.setCategorys(categorys);
		return article;
	}
}
