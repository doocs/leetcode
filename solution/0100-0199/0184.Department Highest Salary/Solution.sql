# Write your MySQL query statement below
with t as (
	select
		departmentId,
		name,
		salary,
		rank() over(
			partition by departmentId
			order by
				salary desc
		) as rk
	from
		Employee
)
select
	d.name Department,
	t.name Employee,
	salary Salary
from
	t
	join Department d on t.departmentId = d.id
where
	rk = 1;