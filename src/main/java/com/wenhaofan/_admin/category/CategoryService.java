package com.wenhaofan._admin.category;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.wenhaofan.common.model.Category;

/**
 * 分类service的实现类
 * @author fwh
 *
 */
public class CategoryService {

	public static CategoryService me=new CategoryService();
	private Category dao=new Category().dao();
	
	
//	public List<Category> getCategorys(String[] categoryIds) {
//		
//		StringBuilder sql=new StringBuilder("select  * from category where pkId in ( ");
//		
//		if(categoryIds!=null) {
//			for(int i=0,size=categoryIds.length;i<size;i++) {
//				sql.append(" "+categoryIds[i]+" ");
//				if((i+1)<size) {
//					sql.append(',');
//				}
//			}
//		}
//		sql.append(')');
//		return dao.find(sql.toString());	
//	}

	public void saveCategory(Category category) {
		category.save();
	}

	public void removeCategoryById(Integer pkId) {
		Category category=new Category();
		category.setPkId(pkId);
		category.delete();
	}

	public void updateCategory(Category category) {
		category.update();
	}

	public List<Category> listCategory() {
		return dao.find("select * from category order by ukPriority");
	}

	
	public Category getCategoryById(Integer pkId) {
		return dao.findById(pkId);
	}

	
	public Integer getMaxPriority() {
		Integer maxPriority=Db.queryInt("select   max(ukPriority)   from  category");
		if(maxPriority==null) {
			maxPriority=0;
		}
		return maxPriority;
	}

	
	public List<Category> listCategorys(Integer  articleId) {
		List<Category> categorys=null;
		
		String sql="select * from article_category where pkId in "
				+ "(select categoryId from article_category where articleId = ?) ";

		categorys=dao.find(sql,articleId);
		
		return categorys;
	}


	
}
