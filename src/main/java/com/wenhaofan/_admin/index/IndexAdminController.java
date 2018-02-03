package com.wenhaofan._admin.index;

import com.wenhaofan.common.controller.BaseController;

public class IndexAdminController extends BaseController{

	public void index() {
		if(notLogin()) {
			renderError(404);
		}
		render("index.html");
	}
}
