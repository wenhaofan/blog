package com.wenhaofan._admin.diy.assets;

import java.io.File;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.upload.UploadFile;
import com.wenhaofan._admin.diy.Constant;
import com.wenhaofan.common.menuTree.Menu;
import com.wenhaofan.common.menuTree.MenuTree;
import com.wenhaofan.common.menuTree.MenuTreeFactory;
import com.wenhaofan.common.uplod.UploadService;

public class AssetsAdminService {

	public static final AssetsAdminService me=new AssetsAdminService();
	
	private UploadService uploadService=UploadService.me;
	private MenuTree assetsTree=null;
	
	private static final String cacheKey="assetsCacheKey";
	
	public void reload() {
		CacheKit.remove(Constant.DIY_CACHE_NAME, cacheKey);
	}
	
	/**
	 * 初始化
	 * @return
	 */
	public MenuTree initAssetsTree(){
		assetsTree=MenuTreeFactory.getMenuTree(PathKit.getWebRootPath()+File.separator+"assets"+File.separator+"uploadAssets");
		return assetsTree;
	}
	 
	public MenuTree getAssets(){
		assetsTree=CacheKit.get(Constant.DIY_CACHE_NAME, cacheKey);
		if(assetsTree==null) {
			initAssetsTree();
			CacheKit.put(Constant.DIY_CACHE_NAME, cacheKey,assetsTree);
		}
		
		
		return assetsTree;
	}
	
	public Ret saveAssets(UploadFile uf) {
		Ret ret= uploadService.uploadAssets(uf);
		CacheKit.remove(Constant.DIY_CACHE_NAME, cacheKey);
		return ret;
	}
	
	public Ret removeAssets(Integer menuId) {
			Menu menu=assetsTree.getMenu(menuId);
			File file=assetsTree.getFile(menu);
			String targetPath=file.getAbsolutePath()+".backup";
			File targetFile=new File(targetPath);
			
			if(targetFile.exists()) {
				targetFile.delete();
			}
			boolean b=file.renameTo(targetFile);
			
			if(b) {
				reload();
			}
			
		return b?Ret.ok():Ret.fail();
	}
}
