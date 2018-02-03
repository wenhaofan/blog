package com.wenhaofan.common.menuTree;

public class Menu {
	
	public static final Integer TYPE_FILE=0;
	public static final Integer TYPE_DIRECTORY=1;
	
	private String absolutePath;
	private String name;
	private Integer type;
	private Integer parentId;
	private Integer currentId;
	
	public boolean isFile() {
		return type==TYPE_FILE;
	}
	
	public boolean isDirectory() {
		return 	type==TYPE_DIRECTORY;
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
