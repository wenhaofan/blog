package com.wenhaofan.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProfiles<M extends BaseProfiles<M>> extends Model<M> implements IBean {

	public void setPkId(java.lang.Integer pkId) {
		set("pkId", pkId);
	}

	public java.lang.Integer getPkId() {
		return getInt("pkId");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setPageName(java.lang.String pageName) {
		set("pageName", pageName);
	}

	public java.lang.String getPageName() {
		return getStr("pageName");
	}

}
