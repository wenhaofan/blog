package com.wenhaofan.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseArticleCategory<M extends BaseArticleCategory<M>> extends Model<M> implements IBean {

	public void setPkId(java.lang.Integer pkId) {
		set("pkId", pkId);
	}

	public java.lang.Integer getPkId() {
		return getInt("pkId");
	}
	public void setArticleId(java.lang.Integer articleId) {

		set("articleId", articleId);
	}

	public java.lang.Integer getArticleId() {
		return getInt("articleId");
	}

	public void setCategoryId(java.lang.Integer categoryId) {
		set("categoryId", categoryId);
	}

	public java.lang.Integer getCategoryId() {
		return getInt("categoryId");
	}

}
