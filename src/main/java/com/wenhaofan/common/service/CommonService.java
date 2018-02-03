package com.wenhaofan.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.TableMapping;

/**
 * 公共service实现类
 * @author Lemon
 *
 */
public class CommonService {

	
	public Map<String, Boolean> checkIsExist(String[] attr,Model<?> model) {
		model.get("pk_id");
		String tableName=TableMapping.me().getTable(model.getClass()).getName();
		model.get("pk_id");
		String fromWhere="select pk_id from " +tableName +" where ";
		
		Map<String,Boolean> map=new HashMap<>();
		StringBuilder fromWhere2=null;
		Integer id=model.getInt("pk_id");
		String attrName=null;
		Object attrValue=null;
		
		List<?> list=null;
		
		for(int i=0,size=attr.length;i<size;i++) {
			fromWhere2=new StringBuilder(fromWhere);
			attrName=attr[i];
			attrValue=model.get(attrName);
			fromWhere2.append(" "+attrName+"= ? ");

			if(id!=null&&StrKit.notBlank(id.toString())) {
				fromWhere2.append(" and pk_id != ? ");
				list=model.find(fromWhere2.toString(),attrValue,id);
			}else {
				list=model.find(fromWhere2.toString(),attrValue);
			}
			
			if(list!=null&&list.size()>0) {
				map.put(attrName, true);
			}else {
				map.put(attrName, false);
			}
		}
		
		return map;
	}
}
