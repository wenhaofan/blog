package com.wenhaofan.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public abstract class BaseSeoInterceptor implements Interceptor{
	public static final String SEO_TITLE = "seoTitle";
	public static final String SEO_KEYWORDS = "seoKeywords";
	public static final String SEO_DESCR = "seoDescr";
	@Override
	public void intercept(Invocation inv) {
		inv.invoke();
		
		indexSeo(inv);
		otherSeo(inv);
	}

	public void setSeoTitle(Controller c,String seoTitle) {
		c.setAttr(SEO_TITLE, seoTitle);
	}
	
	public void setSeoKeyWords(Controller c,String seoKeywords) {
		c.setAttr(SEO_KEYWORDS, seoKeywords);
	}
	
	public void setSeoDescr(Controller c,String seoDescr) {
		c.setAttr(SEO_DESCR, seoDescr);
	}
	
	public abstract void indexSeo(Invocation inv);
	
	public abstract void otherSeo(Invocation inv);
}
