#sql("listTopArticle")
	select
		 pkId,ukTitle,gmtCreate,readNum,content
	from
		 article 
	where 
		isTop = #para(top) 
	and 
		isValid = #para(valid)
		
	#@queryCategory()
	
	order by gmtCreate desc 
	
	#@page()
#end


#sql("listArticle")
	select 
		pkId,
		ukTitle,
		content,
		gmtModified,
		readNum,
		imageUrl,
		gmtCreate
	from
		article
	where
		1=1
	
		#@valid("")
		#if(articles!=null&&!articles.isEmpty())

		and pkId not in (
					#for(article:articles)
						#(article.pkId) 
						#if(for.last!=true)
						,
						#end
					#end)
		#end
	#@queryCategory()
#end



