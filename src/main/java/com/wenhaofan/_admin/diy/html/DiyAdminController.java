package com.wenhaofan._admin.diy.html;

import java.util.List;

import com.jfinal.kit.Ret;
import com.wenhaofan.common.controller.BaseController;
import com.wenhaofan.common.menuTree.Html;
import com.wenhaofan.common.menuTree.Menu;
import com.wenhaofan.common.menuTree.MenuTree;

public class DiyAdminController extends BaseController{

	DiyAdminService service=DiyAdminService.me;
	
	public void index() {
		MenuTree viewTree=service.getViewTree();
	
		setAttr("viewTree", viewTree);
		render("diy.html");
	}
	
	public void reload() {
		service.reload();
		index();
	}
	
	public void listDirectory() {
		Integer menuId=getParaToInt(0);
		List<Menu> menus=service.viewTree.listChildsByParentId(menuId);
		
		renderJson(Ret.ok().set("menus", menus).toJson());
	}
	
	public void update() {
		Integer currentId=getParaToInt(0);
		Html html=service.getContent(currentId);
		
		Ret json=Ret.ok().set("html", html);
		renderJson(json.toJson());
	}
	
	public void doUpdate() {
		Html html=getBean(Html.class,"");
		
		try {
			Integer menuId=getParaToInt("menuId");
			
			Ret ret=service.saveHtml(html, menuId);
			renderJson(ret.toJson());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
