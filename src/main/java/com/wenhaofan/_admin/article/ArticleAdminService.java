package com.wenhaofan._admin.article;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.wenhaofan.common._config.BlogConstant;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.model.ArticleCategory;
import com.wenhaofan.common.service.CategoryService;

/**
 * 文章service实现类
 * 
 * @author fwh
 *
 */
public class ArticleAdminService {
	
	public static final ArticleAdminService me=new ArticleAdminService();
	
	private Article  dao=new Article().dao();

	
	private CategoryService categoryService=CategoryService.me;
	
	
	/**
	 * 添加一篇文章
	 * @param article
	 * @param categoryIds
	 */
	public void saveArticle(Article article,Integer[] categoryIds)  {
		if(StrKit.isBlank(article.getImageUrl())) {
			article.setImageUrl("/assets/image/java-logo-6-140x98.jpg");
		}
		article.setGmtModified(new Date());
		article.save();
		int articleId=article.getPkId();
		saveArticleCategorys(articleId, categoryIds);
	}

	public void saveArticleCategorys(Integer articleId,Integer[] categoryIds) {
		
		ArticleCategory ac=new ArticleCategory();
		ac.setArticleId(articleId);
		
		Integer categoryId=null;
		
		for(int i=0,size=categoryIds.length;i<size;i++) {
			categoryId=categoryIds[i];
			ac.setPkId(null);
			ac.setCategoryId(categoryId);
			ac.save();
		}

	}
	
	public void removeArticleCategorys(Integer articleId) {
		Db.update("delete from  article_category where articleId=?", articleId);
	}
	
	public void removeArticle(Article article) {
		article.delete();
	}

	
	public void deleteArticle(Integer pkId) {
		Article article=new Article();
		article.setPkId(pkId);
		article.setIsValid(BlogConstant.VALID_NO);
		article.update();
	}
	public void recoverArticle(Integer pkId) {
		Article article=new Article();
		article.setPkId(pkId);
		article.setIsValid(BlogConstant.VALID_YES);
		article.update();
	}
	
	public void updateArticle(Article article,Integer[] categoryIds) {
		article.update();
		//删除该文章下的所有分类关联记录
		int articleId=article.getPkId();
		//更新文章关联的分类
		removeArticleCategorys(articleId);
		saveArticleCategorys(articleId, categoryIds);
	
	}

	
	
	public Article getArticleById(int articleId) {
		Article article= dao.findById(articleId);
		return categoryService.setCategory(article);
	}

	

	
	public Page<Article> listArticle(Article article, Integer categoryId, int pageNumber, int pageSize) {

		Kv cond=Kv.by("categoryId", categoryId)
				.set("ukTitle", article.getUkTitle())
				.set("valid", article.getIsValid());
		
		SqlPara sql=dao.getSqlPara("adminArticle.listArticle", cond);
		
		Page<Article> articlePage = dao.paginate(pageNumber, pageSize,sql);
		
		categoryService.setCategorys(articlePage);
		
		return articlePage;
	}



	
	public int countArticle(Article article) {
		List<Article> articles = article.find("select count(pkId) as count from article where isValid=?",
				PropKit.get("is_valid"));

		if (articles != null && articles.size() > 0) {
			return articles.get(0).getInt("count");
		}
		return 0;
	}

	
	

		
}
