#define page()
		limit #para((pageSize-1)*pageNum),#para(pageNum)
#end

文章分类通用组件
#define queryCategory()
	#if(categoryId!=null)
		and pkId in
					(
						select 
							articleId 
						from	
							(select 
									articleId
							 from
									article_category
							 where
									categoryId=#para(categoryId)
							) as articleId
					)
	#end
#end


#define valid(tableName)
	and 
	#if(com.jfinal.kit.StrKit::isBlank(tableName))
		isValid
	#else
		#(tableName).isValid
	#end
	=#(com.wenhaofan.common._config.BlogConstant::VALID_YES)
#end