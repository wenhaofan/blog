package com.wenhaofan._admin.diy.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.ehcache.CacheKit;
import com.wenhaofan._admin.diy.Constant;
import com.wenhaofan.common.menuTree.Html;
import com.wenhaofan.common.menuTree.Menu;
import com.wenhaofan.common.menuTree.MenuTree;
import com.wenhaofan.common.menuTree.MenuTreeFactory;

public class DiyAdminService {
	
	public static DiyAdminService me=new DiyAdminService();
	
	public MenuTree viewTree;
	
	private String rootPath=PathKit.getWebRootPath();

	private static final String cacheKey="viewCacheKey";
	
	
	public void reload() {
		CacheKit.remove(Constant.DIY_CACHE_NAME, cacheKey);
	}
	
	/**
	 * 初始化
	 * @return
	 */
	public MenuTree initViewTree() {
		viewTree= MenuTreeFactory.getMenuTree(rootPath+File.separator+"_view",rootPath+File.separator+"assets");
		return viewTree;
	}
	
	public MenuTree getViewTree() {
		viewTree =CacheKit.get(Constant.DIY_CACHE_NAME,cacheKey);
		if(viewTree==null) {
			initViewTree();
			CacheKit.put(Constant.DIY_CACHE_NAME, cacheKey, viewTree);
		}
		return viewTree;
	}

	
	/**
	 * 获取json文件内容并以String类型返回
	 * @param fileName
	 * @return
	 */
	public  Html getContent(Integer  currentId) {
		Menu menu=viewTree.getMenu(currentId);

		Html html=new Html();
		html.setContent(getHtmlContent(menu).trim());;
		
		return html;
	}

	private String getHtmlContent(Menu menu) {
		File file = new File(menu.getAbsolutePath());
		StringBuilder result = new StringBuilder();
		
		

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				if(s.length()==0) {
					continue;
				}
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public Ret saveHtml(Html html,Integer menuId) {
		Menu menu=getViewTree().getMenu(menuId);
		
		
		html.setMenu(menu);
		
		boolean isSuccess= viewTree.updateMenu(html);
		
		return isSuccess?Ret.ok():Ret.fail();
	}
}
