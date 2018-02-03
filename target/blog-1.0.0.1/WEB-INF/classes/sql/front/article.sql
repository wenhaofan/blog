#sql("lastNextArticle")
	select 
		*
	from
		article
	where
		1=1
		#@valid("")
		and pkId in (
			select 
				articleId
			from
				article_category
			where
				categoryId in (
						select
							categoryId 
						from 
							article_category
						where 
							articleId=#para(0)
						)
			GROUP BY articleId
				)
	order by readNum desc
	limit 2
#end