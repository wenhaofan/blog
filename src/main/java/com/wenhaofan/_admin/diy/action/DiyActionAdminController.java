package com.wenhaofan._admin.diy.action;

import java.io.File;

import com.jfinal.upload.UploadFile;
import com.wenhaofan._admin.diy.assets.AssetsAdminService;
import com.wenhaofan.common.controller.BaseController;
import com.wenhaofan.common.model.HtmlAction;
import com.wenhaofan.common.uplod.UploadService;

public class DiyActionAdminController extends BaseController{

	private DiyActionAdminService service=DiyActionAdminService.me;
	private AssetsAdminService assetsService=AssetsAdminService.me;
	
	public void reload() {
		service.reload();
		list();
	}
	
	public void add() {
		UploadFile uf=getFile("upfile",UploadService.tempPath);
		HtmlAction ha=getModel(HtmlAction.class,"ha");
		renderJson(service.save(ha,uf).toJson());
	}
	

	public void list() {
		setAttr("htmlActions",service.listHtmlAction());
		setAttr("assetsMenuTree",assetsService.getAssets());
		setAttr("assetsRelativePath", File.separator+"assets"+File.separator+"uploadAssets");
		render("diy_html_action.html");
	}
	
	public void update() {
		HtmlAction ha=service.getHtmlAction(getParaToInt(0));
		
		renderJson(ha.toJson());
	}
	
	public void doUpdate() {
		getFile("upfile",UploadService.tempPath);
		HtmlAction action=getModel(HtmlAction.class,"ha");
		renderJson(service.update(action).toJson());
	}
	
	public void remove() {
		renderJson(service.remove(getParaToInt(0)).toJson());
	}
}
