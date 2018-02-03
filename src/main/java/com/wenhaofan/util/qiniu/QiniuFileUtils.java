package com.wenhaofan.util.qiniu;

import java.io.File;
import java.util.List;

import com.google.gson.Gson;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;


/**
 * 处理上传文件至七牛云的类
 * @author fwh
 *
 */
public class QiniuFileUtils {



	/**
	 * 上传文件至七牛云

	 * @param file 需要上传的文件
	 * @param fileName 保存在七牛云的文件的文件名
	 * @return true为成功  false为失败 
	 */
	public static boolean uploadFile(File file, String fileName) {
		
		
		
		String accessKey =null;
		String secretKey = null;
		String bucket = null;
		
		String localFilePath = file.getAbsolutePath();
		// 如果未指定文件名则从file中获取文件名
		String key = null;
		
		if (fileName != null) {
			key = fileName;
		}else{
			key=file.getName();
		}
		
		return uploadFile(accessKey,secretKey,bucket,localFilePath,key);
	}
	
	public static boolean uploadFile(List<File> files) {
		boolean b=false;
		
		for(int i=0,size=files.size();i<size;i++) {
			b=uploadFile(files.get(i),null);
		}
		return b;
	}
	/**
	 * 上传文件至七牛云
	 * @param accessKey 七牛云的ak
	 * @param secretKey 七牛云的sk
	 * @param bucket 七牛云空间名称
	 * @param localPath 上传文件的本地路径
	 * @param key 该文件在七牛云空间保存的名称
	 * @return true为成功上传，false为上传失败
	 */
	public static   boolean uploadFile(String accessKey,String secretKey,String bucket,String localPath,String key){
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		UploadManager uploadManager = new UploadManager(cfg);

		
		// ...生成上传凭证，然后准备上传
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);

		try {
			Response response = uploadManager.put(localPath, key, upToken);
			// 解析上传成功的结果
//			DefaultPutRet putRet = 
			new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			return true;
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r);
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore

			}
		}

		return true;
	}
	
	/**
	 * 删除七牛云中名为fileName的文件
	 * ak sk bucket自动从博客配置文件中读取
	 * @param fileName文件名
	 */
	public  static void removeFile(String fileName ){
		
		
		String accessKey = null;
		String secretKey = null;
		String bucket = null;
		
		removeFile(accessKey,secretKey,bucket,fileName);
	}
	
	/**
	 * 删除七牛云bucket中文件名为fileName的文科
	 * @param accessKey 七牛云的ak
	 * @param secretKey 七牛云中的sk
	 * @param bucket 存储空间名称
	 * @param fileName 文件名称
	 * @return true为成功删除，false为删除失败
	 */
	public static   boolean removeFile(String accessKey,String secretKey,String bucket,String fileName){
		
		Configuration cfg = new Configuration(Zone.zone0());
		
		Auth auth = Auth.create(accessKey, secretKey);

		BucketManager bucketMgr = new BucketManager(auth, cfg);
		
		try {
			Response res=bucketMgr.delete(bucket,fileName);
			
			if(!res.isOK()){
				return false;
			}
		} catch (QiniuException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
