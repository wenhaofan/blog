package com.wenhaofan.login;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.SqlPara;
import com.jfinal.plugin.ehcache.CacheKit;
import com.wenhaofan.common.model.LoginRecord;
import com.wenhaofan.common.model.Session;
import com.wenhaofan.common.model.User;

public class LoginService {
	
	public  final static LoginService me=new LoginService();
	private User dao =new User().dao();
	//private LoginRecord log=new LoginRecord().dao();
	
	
	public static final  String loginUserKey="loginUser";
	public static final String sessionIdName="fwhBlogId";
	public Ret  login(String ukAccount,String pwd,boolean isKeep,String ip) {

		
		Kv vals=Kv.by("ukAccount",ukAccount)
				.set("pwd",pwd);
		SqlPara sql=dao.getSqlPara("login.login",vals);
		
		User loginUser= dao.findFirst(sql);
		
		if(loginUser==null) {
			return Ret.fail("msg", "账号或密码错误!");
		}
		
		LoginRecord log=new LoginRecord();
		log.setIp(ip);
		log.setTime(new Date());
		log.setUserId(loginUser.getPkId());
		log.save();
		Session session=new Session();
		session.setId(StrKit.getRandomUUID());
		session.setUserId(loginUser.getPkId());
		
		long liveSecond=isKeep?365*24*60*60:60*60;
		
		session.setExpireAt(System.currentTimeMillis()+liveSecond*1000);
		
		CacheKit.put(LoginService.loginUserKey,session.getId(), loginUser);
		
		if(!session.save()) {
			return Ret.fail("msg","session保存失败!");
		}
		
		
		return Ret.ok("cookieMaxAge", liveSecond)
				.set(LoginService.loginUserKey, loginUser).set(sessionIdName, session.getId());
	}
	
	
	public User getUserWithSessionId(String sessionId) {
		return CacheKit.get(LoginService.loginUserKey, sessionId);
	}
	
	public User loginWithSessionId(String sessionId) {
		Session session=Session.dao;
		session=session.findById(sessionId);
		if(session==null) {
			return null;
		}
		if(session.isExpired()) {
			session.delete();
			return null;
		}
		Object userId=session.getUserId();
		User loginUser=  dao.findById(userId);
		if(loginUser==null) {
			return null;
		}
		CacheKit.put(LoginService.loginUserKey, session.getId(), loginUser);
		return loginUser;
	}
	
	public User login(User user) {
		Kv vals=Kv.by("ukAccount",user.getUkAccount())
				.set("pwd",user.getPwd());
		SqlPara sql=dao.getSqlPara("login.login",vals);
		
		user= dao.findFirst(sql);
		return user;
	}
	
}
