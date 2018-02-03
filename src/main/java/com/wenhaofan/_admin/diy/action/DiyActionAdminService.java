package com.wenhaofan._admin.diy.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.upload.UploadFile;
import com.wenhaofan.common.model.HtmlAction;
import com.wenhaofan.common.uplod.UploadService;

public class DiyActionAdminService {
	
	public static final DiyActionAdminService me=new DiyActionAdminService();
	private UploadService uploadService=UploadService.me;
	private HtmlAction dao=new HtmlAction().dao();
	
	public static String viewActionCacheName="viewActionCacheName";
	public static String viewActionCaheKey="viewActionCaheKey";
	
	
	public void reload() {
		CacheKit.remove(viewActionCacheName, viewActionCaheKey);
	}
	
	
	public Ret save(HtmlAction action,UploadFile uf) {
		Ret ret=uploadService.uploadHtmlAction(uf);
		
		action.setPath(ret.getStr("relativePath")+File.separator+ret.getStr("fileName"));
		action.setAction("/"+action.getAction());
		boolean b=action.save();
		
		if(!b) {
			ret.setFail();
		}

		//缓存中新增一条添加记录,不做重新加载
		List<HtmlAction> htmlActions=listHtmlAction();
		htmlActions.add(action);
		action.setGmtCreate(new Date());
		return ret;
	}
	
	public Ret update(HtmlAction action) {	
		Ret ret= action.update()?Ret.ok():Ret.fail();
		reload();
		return ret;
	}
	
	public Ret remove(Integer actionId) {
		HtmlAction action=new HtmlAction();
		action.setPkId(actionId.longValue());
		reload();
		return action.delete()?Ret.ok():Ret.fail();
	}
	
	
	public List<HtmlAction> listHtmlAction(){
		return  dao.findByCache(viewActionCaheKey, viewActionCaheKey, "select * from html_action");
	}
	
	 
	
	public boolean dispatcherAction(HttpServletRequest request,ServletResponse response) throws ServletException,IOException{
		String uri=request.getRequestURI();
		
		List<HtmlAction> htmls=null;
		try {
			htmls=DiyActionAdminService.me.listHtmlAction();
		} catch (NullPointerException e) {
			System.err.println("数据库未初始化");
			return false;
		}
		
		
		HtmlAction html=null;
		
		for(int sub=0,size=htmls.size();sub<size;sub++) {
			html=htmls.get(sub);
			if(html.getAction()!=null&&html.getAction().equals(uri)) {
				 String path=html.getPath();
				 
				request.getRequestDispatcher(path).forward(request, response);
			
				return true;
				
			}
		}
		
		return false;
	}
	
	public HtmlAction getHtmlAction(Integer pkId) {
		return dao.findById(pkId);
	}
}
