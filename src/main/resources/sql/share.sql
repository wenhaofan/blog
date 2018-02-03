#sql("listCategory")
	select * from category
#end

#sql("listHotArticle")
	select pkId,ukTitle,gmtCreate,readNum,content from article order by readNum desc limit 0,5
#end

#sql("getBlogger")
	select * from blogger limit 1
#end

#sql("listBlogroll")
	select * from blogroll
#end

#sql("listCategoryByArticleId")
	SELECT
		category.*
	FROM
		category,
		article_category 
	WHERE
		article_category.articleId =#para(0)
		AND 
		category.pkId = article_category.categoryId
#end