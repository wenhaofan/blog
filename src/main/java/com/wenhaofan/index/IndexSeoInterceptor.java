package com.wenhaofan.index;

import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.wenhaofan.common.interceptor.BaseSeoInterceptor;

public class IndexSeoInterceptor extends BaseSeoInterceptor {

	@Override
	public void indexSeo(Invocation inv) {
		Controller c=inv.getController();
		
		setSeoKeyWords(c,"范文皓,范文皓的个人博客,enjoy博客,个人博客,JAVA博客");
		setSeoTitle(c,"范文皓的个人博客");
		setSeoDescr(c, "范文皓创建的个人博客,基于jfinal框架开发");
	}

	@Override
	public void otherSeo(Invocation inv) {
		
	}
}
