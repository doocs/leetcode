# Write your MySQL query statement below
select
    id,
    month,
    sum(salary) over (
        partition by id
        order by
            month range 2 preceding
    ) as Salary
from
    employee
where
    (id, month) not in (
        select
            id,
            max(month)
        from
            Employee
        group by
            id
    )
order by
    id,
    month desc