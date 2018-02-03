package com.wenhaofan.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.wenhaofan.common._config.BlogConstant;
import com.wenhaofan.common.model.Article;
import com.wenhaofan.common.safe.JsoupFilter;
import com.wenhaofan.common.service.CategoryService;

public class IndexService {

	public static IndexService me=new IndexService();
	
	private Article  dao=new Article().dao();
	
	private CategoryService categoryService=CategoryService.me;
	
	/**
	 * 分页通过分类id查询
	 * @param categoryId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Article> pageArticle(Integer categoryId,List<Article> notIn, int pageNum, int pageSize) {
		Kv kv=Kv.by("categoryId", categoryId)
				
				.set("articles", notIn);
		SqlPara sqlPara=dao.getSqlPara("index.listArticle",kv );
		Page<Article> articlePage= dao.paginate(pageNum,pageSize,sqlPara);
		categoryService.setCategorys(articlePage.getList());
		JsoupFilter.filterArticleList(articlePage.getList(), 20, 150);
		return articlePage;
	}
	
	
	/**
	 * 获取查询分类下的两条置顶的文章
	 * @param category
	 * @return
	 */
	public List<Article> getTopArticle(Integer categoryId) {
		
		
		Map<String,Integer> map=new HashMap<>(6,2);
		
		map.put("categoryId", categoryId);
		map.put("valid", BlogConstant.VALID_YES);
		map.put("top", Article.TOP_YES);
		map.put("pageSize", 1);
		map.put("pageNum",2);
		

		SqlPara sqlPara=dao.getSqlPara("index.listTopArticle",map);
		
		List<Article> articles=dao.find(sqlPara);

		categoryService.setCategorys(articles);
		
		JsoupFilter.filterArticleList(articles, 20, 150);
		return articles;
	}
	
	
}
