package com.wenhaofan._admin.blogger;

import com.jfinal.core.Controller;
import com.wenhaofan.common.model.Blogger;

/**
 * 博主信息管理的类
 * @author fwh
 *
 */
public class BloggerController extends Controller{

	public BloggerInfoService service=BloggerInfoService.me;
	public void index(){
		Blogger bloger=service.getBloggerInfo();
		setAttr("blogger",bloger);
		render("blogger.html");
	}
	
	public void update(){
		Blogger blogger=getModel(Blogger.class,"",true);
		
		service.saveBloggerInfo(blogger);	
		
		setAttr("mes", "保存成功!");
		index();
	}
	
	
}
