package com.wenhaofan.util.controller;

import java.util.UUID;

import com.jfinal.core.Controller;
/**
 * Controller层使用的工具方法
 * @author fwh
 *
 */
public class ControllerUtils {
	
	/**
	 * 生成一个随机uuid并存放进session中
	 * @param con
	 */
	public static void setUUid(Controller con){
		String uuid=UUID.randomUUID().toString();
		con.setAttr("uuid",uuid);
		con.setSessionAttr("uuid",uuid);
	}
	/**
	 * 从页面请求中获取uuid并与session中保存的uuid进行比较，如果匹配则为true否则为false,如果为true则从session中移除
	 * @param con 带请求的控制器
	 * @return true为页面UUID等于session中的uuid，false为不等于
	 */
	public static boolean checkUuid(Controller con){
		String pageUUid=con.getPara("uuid");
		String sessionUUid=con.getSessionAttr("uuid");
		if(pageUUid==null||sessionUUid==null||!pageUUid.equals(sessionUUid)){
			return false;
		}
		con.removeSessionAttr("uuid");
		return true;
	}
	
	
}
