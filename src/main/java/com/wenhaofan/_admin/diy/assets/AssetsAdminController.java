package com.wenhaofan._admin.diy.assets;

import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;
import com.wenhaofan.common.controller.BaseController;
import com.wenhaofan.common.uplod.UploadService;

public class AssetsAdminController extends BaseController{

	private AssetsAdminService service=AssetsAdminService.me;
	
	public void remove() {
		Integer menuId=getParaToInt(0);
		Ret ret=service.removeAssets(menuId);
		renderJson(ret.toJson());
	}
	public void add() {
		UploadFile uf=getFile("upfile",UploadService.tempPath);
		renderJson(service.saveAssets(uf).toJson());
	}
	public void reload() {
		service.reload();
		forwardAction("/admin/diy/action/list");
	}
}
