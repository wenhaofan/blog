package com.wenhaofan.common.service;

import java.util.List;

import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.model.Basic;
import com.wenhaofan.common.model.Blogger;
import com.wenhaofan.common.model.Blogroll;
import com.wenhaofan.common.model.Category;

public class IndexService {
	
	public static IndexService me=new IndexService();
	
	private Article dao=new Article().dao();
	
	private Category categoryDao=new Category().dao();

	private Blogger bloggerDao=new Blogger().dao();

	private Blogroll blogrollDao=new Blogroll().dao();
	
	private Basic basicDao=new Basic().dao();
	/**
	 * 获取热门文章的标题
	 * @return
	 */
	public List<Article> listHotArticle() {
		String sql=dao.getSql("share.listHotArticle");
		return dao.find(sql);
	}

	public List<Category> listCategory(){
		String sql=dao.getSql("share.listCategory");
		return categoryDao.find(sql);
	}
	
	/**
	 * 获取第一条博主信息
	 * @return
	 */
	public Blogger getBlogger() {
		String sql=dao.getSql("share.getBlogger");
		return bloggerDao.findFirst(sql);
	}
	
	/**
	 * 获取所有友情链接
	 * @return
	 */
	public List<Blogroll> listBlogroll(){
		String sql=dao.getSql("share.listBlogroll");
		return blogrollDao.find(sql);
	}
	
	/**
	 * 获取一条网站配置
	 * @return
	 */
	public Basic getBasic() {
		return basicDao.findFirst("select * from basic limit 1");
	}
}
