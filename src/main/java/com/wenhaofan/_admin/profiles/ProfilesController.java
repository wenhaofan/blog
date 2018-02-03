package com.wenhaofan._admin.profiles;

import com.jfinal.core.Controller;
import com.wenhaofan.common.model.Profiles;


/**
 * 关于我页面的配置
 * @author fwh
 *
 */
public class ProfilesController extends Controller{

	private final ProfilesService service =ProfilesService.me;
	
	public void profiles() {
		Profiles profiles=service.getProfiles();
		setAttr("profiles",profiles);
		render("profiles_show.html");
	}
	
	public void update() {
		Profiles profiles=getModel(Profiles.class,"");
		service.saveOrUpdateProfiles(profiles);
		render("profiles_show.html");
	}
}
