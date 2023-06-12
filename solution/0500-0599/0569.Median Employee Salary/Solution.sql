# Write your MySQL query statement below
with t as (
    select
        *,
        row_number() over(
            partition by company
            order by
                salary asc
        ) rk,
        count(id) over(partition by company) n
    from
        Employee
)
select
    id,
    company,
    salary
from
    t
where
    rk >= n / 2
    and rk <= n / 2 + 1;