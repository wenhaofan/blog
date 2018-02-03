package com.wenhaofan.common._config;

import com.jfinal.kit.PathKit;

public abstract class BlogConstant {

	/**有效*/
	public static final Integer VALID_YES=0;
	/**无效*/
	public static final Integer VALID_NO=1;
	
	public static Boolean IS_INIT=false;
	
	public static final  String UPLOAD_ROOT_PATH=PathKit.getWebRootPath();
}
