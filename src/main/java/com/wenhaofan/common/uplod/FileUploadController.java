package com.wenhaofan.common.uplod;


import java.io.File;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;
import com.wenhaofan.common._config.BlogConstant;
import com.wenhaofan.common.interceptor.LoginInterceptor;


/**
 * 文件上传控制器
 * @author fwh
 *
 *
 */

@Clear(LoginInterceptor.class)
public class FileUploadController extends Controller {

	private UploadService service=UploadService.me;
	
	/**
	 * /admin/common/upload/ueditor
	 * 富文本编辑器文件上传处理的controller
	 */
	
	public void ueditor() {
	
		if ("config".equals(getPara("action"))) {
			
			render("/assets/ueditor/jsp/config.json");

			return;

		}

		 //"upfile" 来自 config.json 中的 imageFieldName 配置项
		
		
		
		boolean b=service.mkdir(BlogConstant.UPLOAD_ROOT_PATH+UploadService.articleContentImage);
		
		UploadFile uf = getFile("upfile",UploadService.articleContentImage);
		//获取文件保存的路径,以及文件类型，文件名
		//文件名
		String fileName = uf.getFileName();
		//文件类型
		String fileType=(fileName.split("\\."))[1];
	
		String localFilePath=UploadService.articleContentImage+File.separator;

		Ret ret = Ret.create("state", "SUCCESS")
		
				.set("url",localFilePath+fileName)

				.set("title",fileName)

				.set("original", uf.getOriginalFileName())

				.set("type", "." + fileType)  //这里根据实际扩展名去写

				.set("size", uf.getFile().length());
		
		if(!b) {
			ret.setFail();
		}
		
		renderJson(ret.toJson());
	}

	/**
	 * 图片上传处理
	 */
	public void fileUpload() {
		
		
		service.mkdir(BlogConstant.UPLOAD_ROOT_PATH+UploadService.articlPreviewIamge);
		
		UploadFile uf = getFile("upfile",UploadService.tempPath);
	
		renderJson(service.uploadArticleContentImage(uf));
	}
	
	public void htmlAction() {
		String path=PathKit.getWebRootPath()+"\\_view";
		File file=new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		//UploadFile uf=getFile("upfile",UploadService.tempPath);
		
		
	}
}



