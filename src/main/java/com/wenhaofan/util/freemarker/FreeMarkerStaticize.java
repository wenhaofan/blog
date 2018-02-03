package com.wenhaofan.util.freemarker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.jfinal.core.JFinal;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemarker生成静态化页面的工具类
 * 
 * @author wenhaofan
 *
 */
public class FreeMarkerStaticize {

	/**
	 * 执行页面静态化操作的方法
	 * 
	 * @param sourceName
	 *            源文件名
	 * @param sourceDir
	 *            源文件目录
	 * @param staticizeName
	 *            目标文件
	 * @param staticizeDir
	 *            目标文件目录
	 * @param map
	 *            值
	 */
	public static void staticize(String sourceName, String sourceDir, String staticizeName, String staticizeDir,
			Map<String, Object> map) {

		Configuration conf = new Configuration(Configuration.getVersion());

		File tempFile = new File(sourceDir);
		Writer out = null;
		try {
			conf.setDirectoryForTemplateLoading(tempFile);
			Template template = conf.getTemplate(sourceName);

			mkdir(staticizeDir);
			out = new FileWriter(new File(staticizeDir + "/" + staticizeName));

			template.process(map, out);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	// 如果不存在
	public static boolean mkdir(String staticizeDir) {
		File targetFile = new File(staticizeDir);
		boolean tfExist = targetFile.exists();
		if (!tfExist) {
			targetFile.mkdir();
		}
		return tfExist;
	}

	public static String getPath() {
		// 获取项目的绝对路径
		String path = JFinal.me().getServletContext().getRealPath(java.io.File.separator);
		path = path + java.io.File.separator;
		return path;
	}
}
