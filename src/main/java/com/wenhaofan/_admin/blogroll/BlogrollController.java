package com.wenhaofan._admin.blogroll;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.wenhaofan.common.annotation.ValidateMethod;
import com.wenhaofan.common.model.Blogroll;
/**
 * 友情链接控制器
 * @author 范文皓
 *
 */

public class BlogrollController extends Controller {

	private BlogrollService blogrollService=BlogrollService.me;
	
	

	public void add(){
		Blogroll blogroll=getModel(Blogroll.class,"",true);
		blogrollService.save(blogroll);
		setAttr("mes","添加成功!");
		index();
	}
	

	
	public void remove(){
		Integer id=getParaToInt(0);
		Blogroll blogroll=new Blogroll();
		blogroll.setPkId(id);
	
		blogrollService.remove(blogroll);
		setAttr("blogrollMes","删除成功!");
	
		index();
	}
	

	public void update() {
		Integer id=getParaToInt(0);
		Blogroll blogroll=blogrollService.getBlogrollById(id);
		Ret result=new Ret().setOk()
					.set("blogroll", blogroll);
		renderJson(result.toJson());
	}
	
	@ValidateMethod(name="doUpdate")
	public void doUpdate() {
		Blogroll blogroll=getModel(Blogroll.class,"",true);
		blogrollService.update(blogroll);
		index();
	}
	
	public void index(){
		List<Blogroll> blogrolls=blogrollService.listBlogroll();
		int maxPriority=blogrollService.getMaxPriority();
		setAttr("maxPriority", maxPriority);
		setAttr("blogrollList", blogrolls);
		render("blogroll_list.html");
	}
	
	
}
