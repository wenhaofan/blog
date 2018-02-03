package com.wenhaofan._admin.blogroll;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.wenhaofan.common.model.Blogroll;

/**
 * 友情链接的实现类
 * @author fwh
 *
 */
public class BlogrollService{

	public static final  BlogrollService me=new BlogrollService();
	private final Blogroll dao=new Blogroll().dao();
	
	public void save(Blogroll blogroll) {
		blogroll.save();
	}

	public void remove(Blogroll blogroll) {
		blogroll.delete();
	}

	public void update(Blogroll blogroll) {
		blogroll.update();
	}

	public List<Blogroll> listBlogroll() {
		// TODO Auto-generated method stub
		return dao.find("select * from blogroll");
	}

	
	public Blogroll getBlogrollById(Integer pkId) {
		// TODO Auto-generated method stub
		return dao.findById(pkId);
	}

	
	public Integer getMaxPriority() {
		Integer ukPriority=Db.queryInt("select   ukPriority   from  blogroll  order   by   ukPriority   desc   limit   1");
		if(ukPriority==null) {
			ukPriority=0;
		}
		return ukPriority;
	}
}
