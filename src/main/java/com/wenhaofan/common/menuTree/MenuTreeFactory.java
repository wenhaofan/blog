package com.wenhaofan.common.menuTree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuTreeFactory {
	
	private  String rootPath;
	
	public static MenuTreeFactory factory=null;
	
	public static MenuTreeFactory getMenuTreeFactory() {
		if(factory==null) {
			factory=new MenuTreeFactory();
		}
		return factory;
	}
	
	/**
	 * 获取目录树
	 * @param paths
	 * @return
	 */
	public static MenuTree getMenuTree(String... paths) {
		
		MenuTreeFactory factory=getMenuTreeFactory();
		List<Menu> menus=factory.listAllMenu(paths);
		
		
		MenuTree tree=new MenuTree();
		
		tree.setMenus(menus);
		tree.setRootPath(factory.getRootPath());
		
		return tree;
	}
	
	/**
	 * 获取paths下的所有文件和文件夹
	 * @param paths
	 * @return
	 */
	private  List<Menu>  listAllMenu(String...  paths) {
		
		
		List<Menu> menus=listFile(paths);

		return menus;
	}
	/**
	 * 获取该路径下所有的文件和目录
	 * @param path
	 * @return
	 */
	public List<Menu> listFile(String... paths){
		
		File[] files=new File[paths.length];
		
		File file=null;
		for(int i=0,size=paths.length;i<size;i++) {
			file=new File(paths[i]);
			files[i]=file;
		}
		
		
		if(!file.exists()) {
			return null;
		}
		return listFile(files);
	}
	
	/**
	 * 获取该路径下所有的文件和目录
	 * @param path
	 * @return
	 */
	public List<Menu> listFile(File... files){
		List<Menu> menus=new ArrayList<>();
		
		return listFile(files, menus);
	}
	
	/**
	 * 获取该路径下所有的文件和目录
	 * @param path
	 * @return
	 */
	public List<Menu> listFile(File[]  sourseFiles,List<Menu> menus) {
		
		if(sourseFiles==null||sourseFiles.length==0) {
			return null;
		}	
		
		File tempSourseFile=null;
		
		
		for(int fileSub=0,fileSize=sourseFiles.length;fileSub<fileSize;fileSub++) {
			
			tempSourseFile=sourseFiles[fileSub];
			
			if(tempSourseFile.isDirectory()) {
				addMenu(tempSourseFile,menus);
				
				listFile(tempSourseFile.listFiles(),menus);
				
			}else {
				addMenu(tempSourseFile,menus);
			}
		}
		
	
		return menus;
		
	}
	
	
	/**
	 * 添加文件和文件夹
	 * @param file
	 */
	public void addMenu(File file,List<Menu> menus) {
		Menu menu=getMenu(file);
		
		if(menu.isFile()) {
			String fileName=menu.getName();
			if(fileName.endsWith(".backup")) {
				return;
			}
		}
		
		Menu parentMenu=getParentMenu(file,menus);	
		
		menu.setCurrentId(menus.size()+1);
		if(parentMenu==null) {
			menu.setParentId(MenuTree.ROOT_ID);
		}else {
			menu.setParentId(parentMenu.getCurrentId());
		}
		
		menus.add(menu);
	}
	
	/**
	 * 获取文件父文件夹的menu对象
	 * @param file
	 * @return
	 */
	public static Menu getParentMenu(File file,List<Menu> menus) {
		
		File parentFile=file.getParentFile();
		
		Menu parentMenu=getMenu(parentFile);
		
		Menu targetMenu=null;

		
		for(int menuSub=0,menuSize=menus.size();menuSub<menuSize;menuSub++) {
			targetMenu=menus.get(menuSub);
			if(targetMenu.equals(parentMenu)) {
				return targetMenu;
			}
		}
		
		return null;
	}
	
	/**
	 * 将file信息封装为menu对象并返回
	 * @param file
	 * @return
	 */
	public static Menu getMenu(File file) {
		Menu menu=new Menu();
		menu.setAbsolutePath(file.getAbsolutePath());
		menu.setName(file.getName());
		menu.setType(file.isDirectory()?Menu.TYPE_DIRECTORY:Menu.TYPE_FILE);
		return menu;
	}

	
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	
	
}
