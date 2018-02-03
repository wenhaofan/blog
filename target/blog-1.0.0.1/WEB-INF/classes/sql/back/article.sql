#sql("listArticle")
	select
		pkId,
		ukTitle,
		content,
		gmtCreate,
		gmtModified,
		readNum,
		imageUrl,
		isValid
	from 
		article 
	where 1=1 
	
	#if(valid!=null)
		 and isValid=#para(valid)
	#else
		and isValid=#(com.wenhaofan.common._config.BlogConstant::VALID_YES)
	#end
	
	#@queryCategory()
	
	#if(ukTitle!=null)
		and ukTitle like %#para(ukTitle)%
	#end
#end