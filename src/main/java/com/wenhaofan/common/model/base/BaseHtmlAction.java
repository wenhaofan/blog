package com.wenhaofan.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseHtmlAction<M extends BaseHtmlAction<M>> extends Model<M> implements IBean {

	public void setPkId(java.lang.Long pkId) {
		set("pkId", pkId);
	}
	
	public java.lang.Long getPkId() {
		return getLong("pkId");
	}

	public void setPath(java.lang.String path) {
		set("path", path);
	}
	
	public java.lang.String getPath() {
		return getStr("path");
	}

	public void setAction(java.lang.String action) {
		set("action", action);
	}
	
	public java.lang.String getAction() {
		return getStr("action");
	}

	public void setGmtCreate(java.util.Date gmtCreate) {
		set("gmtCreate", gmtCreate);
	}
	
	public java.util.Date getGmtCreate() {
		return get("gmtCreate");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

}