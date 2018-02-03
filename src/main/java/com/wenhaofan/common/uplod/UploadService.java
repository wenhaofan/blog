package com.wenhaofan.common.uplod;

import java.io.File;

import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;
import com.wenhaofan.common._config.BlogConstant;

public class UploadService {
	
	public static final UploadService me=new UploadService();
	
	public static final String tempPath=File.separator+"tmep";
	
	public static final  String articlPreviewIamge=File.separator+"upload"+File.separator+"article"+File.separator+"preview"+File.separator+"image";
	
	public  static final String articleContentImage=File.separator+"upload"+File.separator+"article"+File.separator+"content"+File.separator+"image";
	
	public static final String actionHtml=File.separator+"_view"+File.separator+"htmlAction";
	public static final String assets=File.separator+"assets"+File.separator+"uploadAssets";
	public Ret uploadArticleContentImage(UploadFile uf) {
		File file=uf.getFile();
		String fileName=StrKit.getRandomUUID()+file.getName();
		Ret ret = moveFile(uf, articlPreviewIamge,fileName);
		return ret;
	}
	
	public Ret uploadHtmlAction(UploadFile uf) {
		Ret ret = moveFile(uf,actionHtml,null);
		return ret;
	}

	public Ret uploadAssets(UploadFile uf) {
		Ret ret = moveFile(uf,assets,null);
		return ret;
	}
	
	private Ret moveFile(UploadFile uf,String path, String fileName) {
		File file=uf.getFile();
		if(fileName==null) {
			fileName=file.getName();
		}
		File targetFile=new File(BlogConstant.UPLOAD_ROOT_PATH+path+File.separator+fileName);
		boolean b=file.renameTo(targetFile);
		
		Ret ret=b?Ret.ok():Ret.fail();
		ret.set("path", path).set("fileName",targetFile.getName()).set("relativePath",actionHtml);
		return ret;
	}
	
	public boolean mkdir(String path) {
		File file=new File(path);
		if(!file.exists()) {
			return file.mkdirs();
		}
		return true;
	}
	
}
