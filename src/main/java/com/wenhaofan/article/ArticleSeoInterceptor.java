package com.wenhaofan.article;

import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.wenhaofan.common.interceptor.BaseSeoInterceptor;
import com.wenhaofan.common.model.Article;

public class ArticleSeoInterceptor extends BaseSeoInterceptor {

	@Override
	public void indexSeo(Invocation inv) {
		
		Controller c=inv.getController();
		Article article=c.getAttr("article");
		if(article!=null) {
			setSeoDescr(c,article.getUkTitle());
			setSeoKeyWords(c, article.getUkTitle());
			setSeoTitle(c,article.getUkTitle());
		}inv.invoke();
	}

	@Override
	public void otherSeo(Invocation inv) {
		// TODO Auto-generated method stub

	}

}
