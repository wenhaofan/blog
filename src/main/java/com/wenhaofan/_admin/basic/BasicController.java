package com.wenhaofan._admin.basic;

import com.jfinal.core.Controller;
import com.wenhaofan.common.model.Basic;

/**
 * 首页信息配置的控制器
 * @author 范文皓
 *
 */
public class BasicController extends Controller {

	BasicService service=BasicService.me;
	public void index() {
		Basic base=service.getBasic();
		if(base==null) {
			base=new Basic();
		}
		setAttr("basic",base);
		
		render("sys_config.html");
	}
	
	public void update() {
		Basic basic=getModel(Basic.class,"",true);
		service.saveOrUpdateBasic(basic);
		Basic queryBasie=service.getBasic();
		setAttr("basic",queryBasie);
		render("sys_config.html");
	}
	

}
