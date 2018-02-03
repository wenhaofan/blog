package com.wenhaofan._admin.profiles;

import com.wenhaofan.common.model.Profiles;

public class ProfilesService {
	public static ProfilesService me=new ProfilesService();
	private Profiles dao=new Profiles().dao();
	
	public Profiles getProfiles() {
		return dao.findFirst("select * from profiles limit 0,1");
	}
	
	public void saveOrUpdateProfiles(Profiles profiles) {
		Integer pkId=profiles.getPkId();
		if(pkId==null) {
			profiles.save();
		}else {
			profiles.update();
		}
	}
}
