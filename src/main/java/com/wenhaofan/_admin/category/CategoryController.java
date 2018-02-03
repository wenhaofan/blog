package com.wenhaofan._admin.category;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.wenhaofan.common.annotation.ValidateMethod;
import com.wenhaofan.common.model.Category;

/**
 * 分类管理的控制器
 * 
 * @author fwh
 *
 */
public class CategoryController extends Controller {

	private CategoryService service = CategoryService.me;

	/**
	 * 查询所有的分类信息
	 */
	public void list() {
		List<Category> categoryList = service.listCategory();
		Integer maxPriority=service.getMaxPriority();
		setAttr("categoryList", categoryList);
		setAttr("maxPriority", maxPriority);
		render("category_list.html");
	}

	/**
	 * 删除
	 */

	public void remove() {
		Integer id = getParaToInt(0);
		service.removeCategoryById(id);
		setAttr("removeMes","删除成功！");
		list();
	}

	/**
	 * 修改分类信息
	 */
	
	public void update() {
		Integer id = getParaToInt(0);
		Category category=service.getCategoryById(id);
		int maxPriority =service.getMaxPriority();
		
		Ret result=new Ret()
				.setOk()
				.set("category", category)
				.set("maxPriority", maxPriority);
		
		renderJson(result.toJson());
	}
	
	
	public void doUpdate(){
	
		Category category =getModel(Category.class,"",true);
		service.updateCategory(category);
		setAttr("mes", "修改成功!");
		list();
	}

	/**
	 * 执行添加操作的控制器
	 */
	@ValidateMethod(name="doAdd")
	public void doAdd(){

		Category category =getModel(Category.class,"",true);
		service.saveCategory(category);
		setAttr("mes", "添加成功!");
		
		list();
	}
	
	
}
