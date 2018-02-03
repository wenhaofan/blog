package com.wenhaofan.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.wenhaofan._admin.diy.action.DiyActionAdminService;

/**
 * Servlet Filter implementation class HtmlActionFilter
 */
@WebFilter("/HtmlActionFilter")
public class HtmlActionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HtmlActionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=	(HttpServletRequest)request;
		
		boolean isAllot=DiyActionAdminService.me.dispatcherAction(req,response);
		
		if(isAllot) {
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
