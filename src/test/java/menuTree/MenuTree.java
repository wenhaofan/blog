package menuTree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class MenuTree {
	Jedis jedis =new Jedis("127.0.0.1");
	
	public static	String  rootPath="I:\\2017-11-25\\blog\\src\\main\\webapp";
	
	List<Menu> menus=new ArrayList<Menu>() {
		private static final long serialVersionUID = 1L;

	{
		Menu menu=new Menu();
		menu.setAbsolutePath(rootPath);
		menu.setParentId(0);
		menu.setCurrentId(1);
		menu.setType(Menu.TYPE_DIRECTORY);
		menu.setName("webapp");
		add(menu);
	}};
	
	@Test
	public void testListMenu() {
		String path="I:\\2017-11-25\\blog\\src\\main\\webapp";
		File file=new File(path);

		System.out.println(file.getAbsolutePath());
//		listFile(file.listFiles());
//		
//		System.out.println(menus);
	}
	
	public void listFile(File[]  sourseFiles) {
		
		if(sourseFiles==null||sourseFiles.length==0) {
			return ;
		}
		
		File tempSourseFile=null;
		
		
		for(int fileSub=0,fileSize=sourseFiles.length;fileSub<fileSize;fileSub++) {
			
			tempSourseFile=sourseFiles[fileSub];
			
			if(tempSourseFile.isDirectory()) {
				addMenu(tempSourseFile);
				
				listFile(tempSourseFile.listFiles());
				
			}else {
				addMenu(tempSourseFile);
			}
		}
		
	
		
		
	}
	
	
	/**
	 * 添加文件和文件夹
	 * @param file
	 */
	public void addMenu(File file) {
		Menu menu=getMenu(file);
		
		Menu parentMenu=getParentMenu(file);	
		if(menus.size()==1) {
			parentMenu=menus.get(0);
		}
		menu.setParentId(parentMenu.getCurrentId());
		menu.setCurrentId(parentMenu.getCurrentId()+1);

		menus.add(menu);
	}
	
	/**
	 * 获取文件父文件夹的menu对象
	 * @param file
	 * @return
	 */
	public Menu getParentMenu(File file) {
		
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
	public Menu getMenu(File file) {
		Menu menu=new Menu();
		menu.setAbsolutePath(file.getAbsolutePath().replace(rootPath,""));
		menu.setName(file.getName());
		menu.setType(file.isDirectory()?Menu.TYPE_DIRECTORY:Menu.TYPE_FILE);
		return menu;
	}
}

class Menu {
	
	public static final Integer TYPE_FILE=0;
	public static final Integer TYPE_DIRECTORY=1;
	
	private String absolutePath;
	private String name;
	private Integer type;
	private Integer parentId;
	private Integer currentId;
	
	
	public File getFile() {
		return new File(MenuTree.rootPath+absolutePath+File.separator+name);
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getCurrentId() {
		return currentId;
	}
	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}


	public boolean equals(Menu obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (absolutePath == null) {
			if (other.absolutePath != null)
				return false;
		} else if (!absolutePath.equals(other.absolutePath))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [absolutePath=" + absolutePath + ", name=" + name + ", type=" + type + ", parentId=" + parentId
				+ ", currentId=" + currentId + "]";
	}

	
}
