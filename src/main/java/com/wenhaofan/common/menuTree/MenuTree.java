package com.wenhaofan.common.menuTree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 目录树
 * 
 * @author Lemon
 *
 */
public class MenuTree {

	private String rootPath;
	
	private List<Menu> menus;

	private String treeName;
	
	/**根目录的父级id*/
	public static final Integer ROOT_ID=0;
	
	
	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public boolean isEmpty() {
		return menus.isEmpty();
	}

	public int getSize() {
		return menus.size();
	}

	public File getFile(Menu menu) {
		return new File(menu.getAbsolutePath());
	}

	/**
	 * 获取该目录节点的父级
	 * 
	 * @param menu
	 * @return
	 */
	public Menu getParent(Menu menu) {
		Integer parentId = menu.getParentId();
		return getMenuByParentId(parentId);
	}

	/**
	 * 寻找id为parentId的目录节点
	 * 
	 * @param parentId
	 * @return
	 */
	public Menu getMenuByParentId(Integer parentId) {

		Menu targetMenu = null;

		for (int menuSub = 0, menuSize = menus.size(); menuSub < menuSize; menuSub++) {
			targetMenu = menus.get(menuSub);
			if (targetMenu.getCurrentId() == parentId) {
				return targetMenu;
			}
		}

		return null;
	}

	/**
	 * 寻找该节点下的所有子节点
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> getChilds(Menu menu) {
		Integer currentId = menu.getCurrentId();
		List<Menu> menus = listChildsByParentId(currentId);
		return menus;
	}

	public List<Menu> listChildsByParentId(Integer parentId) {

		List<Menu> childMenus = new ArrayList<>();
		Menu targetMenu = null;

		for (int menuSub = 0, menuSize = menus.size(); menuSub < menuSize; menuSub++) {
			targetMenu = menus.get(menuSub);
			if (targetMenu.getParentId() == parentId) {
				childMenus.add(targetMenu);
			}
		}

		return childMenus;
	}

	/**
	 * 获取根目录
	 * @return
	 */
	public List<Menu> getRootMenu() {
		
		List<Menu> rootFiles=new ArrayList<>();
		
		Menu targetMenu = null;
		for (int menuSub = 0, menuSize = menus.size(); menuSub < menuSize; menuSub++) {
			targetMenu = menus.get(menuSub);
			if (targetMenu.getParentId() == ROOT_ID) {
				rootFiles.add(targetMenu);
			}
		}
		
		return rootFiles;
	}


	
	public Menu getMenu(Integer currentId) {
		Menu targetMenu = null;
		for (int menuSub = 0, menuSize = menus.size(); menuSub < menuSize; menuSub++) {
			targetMenu = menus.get(menuSub);
			if (targetMenu.getCurrentId() == currentId) {
				return targetMenu;
			}
		}

		return null;
	}

	/**
	 * 更新,并增加一个以.backup结尾的备份文件
	 * @param html
	 * @return
	 */
	public boolean updateMenu(Html html) {
		Menu menu = html.getMenu();
		File file = getFile(menu);
		File targetFile = new File(menu.getAbsolutePath() + ".backup");
		if(targetFile.exists()) {
			boolean b=targetFile.delete();
		}
		
		boolean isRename = file.renameTo(targetFile);

		if (!isRename) {
			return isRename;
		}

		file = getFile(menu);

		try {

			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(html.getContent());
			writer.flush();
			writer.close();

			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
}
