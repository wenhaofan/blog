package com.wenhaofan._admin.blogger;

import com.wenhaofan.common.model.Blogger;

public class BloggerInfoService {
	public static final BloggerInfoService me=new BloggerInfoService();
	private final Blogger dao=new Blogger().dao();
	
	public Blogger getBloggerInfo() {
		return dao.findFirst("select * from blogger limit 1");
	}
	
	public void saveBloggerInfo(Blogger BloggerInfo) {
		Integer pkId=BloggerInfo.getPkId();
		
		if(pkId!=null) {
			BloggerInfo.update();
		}else {
			BloggerInfo.save();
		}
	}
}
