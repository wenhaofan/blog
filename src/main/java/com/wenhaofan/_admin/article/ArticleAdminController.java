package com.wenhaofan._admin.article;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wenhaofan._admin.category.CategoryService;
import com.wenhaofan.common.annotation.ValidateMethod;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.model.Category;


/**
 * 文章后台管理的控制器
 * 
 * @author fwh
 *
 */
public class ArticleAdminController extends Controller {

	private ArticleAdminService articleService = ArticleAdminService.me;

	private CategoryService categoryService = CategoryService.me;

	/**
	 * 分页查询
	 */
	public void list() {
		Article article = getModel(Article.class,"query");
		Integer categoryId = getParaToInt("categoryId");
	
		Integer pageNum=getParaToInt(0,1);

		Page<Article> articlePage = articleService.listArticle(article, categoryId, pageNum,8);
		List<Category> categorys=categoryService.listCategory();
		
		keepModel(Article.class,"query");
		
		keepPara("categoryId");
		
		setAttr("categorys",categorys);
		setAttr("articlePage", articlePage);
		render("article_list.html");
	}
	/**
	 * 跳转进文章添加页面
	 */
	public void add() {
		List<Category> categorys = categoryService.listCategory();
		setAttr("categorys", categorys);
		render("article_add.html");
	}

	/**
	 * 执行文章添加
	 * 
	 * @throws Exception
	 */

	@ValidateMethod(name="doAdd")
	public void doAdd() {

		Article article = getModel(Article.class);
		Integer[] checkedCategorys =getParaValuesToInt("categoryId");
		articleService.saveArticle(article, checkedCategorys);
		setAttr("mes", "添加成功!");
		list();
	}


	/**
	 * 删除文章
	 */
	public void delete() {
		Integer id = getParaToInt("d");
		articleService.deleteArticle(id);
		setAttr("mes", "删除成功!");
		list();
	}

	/**
	 * 恢复成功
	 */
	public void recover() {
		Integer id = getParaToInt("d");
		articleService.recoverArticle(id);
		setAttr("mes", "恢复成功!");
		list();
	}


	/**
	 * 获取需要更新的文章id并跳转至修改页面
	 */

	@ValidateMethod(name="update")
	public void update() {

		Integer articleId = getParaToInt(0);
		
		Article article = articleService.getArticleById(articleId);

		List<Category> categorys=categoryService.listCategory();
	
		
		setAttr("article", article);
		setAttr("categorys", categorys);

		render("article_update.html");
	}
	/**
	 * 执行更新操作
	 */

	@ValidateMethod(name="doUpdate")
	public void doUpdate() {
		Article article = getModel(Article.class);
		Integer[] checkedCategoryIds = getParaValuesToInt("categoryId");
		

		Article updateArticle =null;
	
		articleService.updateArticle(article,checkedCategoryIds);
		
		setAttr("article", updateArticle);
		setAttr("mes", "修改成功!");
		list();
	}



}
