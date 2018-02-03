package com.wenhaofan._admin.basic;

import com.wenhaofan.common.model.Basic;

public class BasicService {
	
	public static final BasicService me=new BasicService();
	private Basic dao=new Basic().dao();
	
	public Basic getBasic() {
		return dao.findFirst("select * from basic limit 0,1");
	}
	
	public void saveOrUpdateBasic(Basic basic) {
		Integer pkId=basic.getPkId();
		if(pkId==null) {
			basic.save();
		}else {
			basic.update();
		}
	}
}
