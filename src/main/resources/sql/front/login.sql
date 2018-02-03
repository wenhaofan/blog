#sql("login")
	select 
		* 
	from
		user
	where
		ukAccount=#para(ukAccount)
	and
		pwd=#para(pwd)
		
		limit 1
#end