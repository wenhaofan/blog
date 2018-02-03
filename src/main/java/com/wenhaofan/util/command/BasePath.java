package com.wenhaofan.util.command;

import com.jfinal.kit.PropKit;
import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.stat.Scope;

public class BasePath extends Directive{



	@Override
	public void exec(Env arg0, Scope arg1, com.jfinal.template.io.Writer writer) {
		//JFinal.me().getServletContext().getInitParameter("basePath")
		write(writer,PropKit.get("basePath"));	
	}

}
