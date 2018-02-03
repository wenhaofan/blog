package com.wenhaofan._admin.user;

import com.jfinal.plugin.activerecord.Db;
import com.wenhaofan.common.model.User;

public class AdminUserService {

	public static final AdminUserService me= new AdminUserService();
	//private final User dao=new User().dao();
	

	public void update(User user) {
		user.update();
	}
	
	
	public Boolean isExistAdmin() {
		Integer count= Db.queryInt("select count(*) from user where level=1");
		return count!=null&&count>0;
	}
	
	public void saveUser(User user) {
		user.save();
	}
}
