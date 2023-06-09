# Write your MySQL query statement below
with t as (
    select
        *,
        sum(frequency) over(
            order by
                num asc
        ) as rk1,
        sum(frequency) over(
            order by
                num desc
        ) as rk2,
        sum(frequency) over() as s
    from
        Numbers
)
select
    round(avg(num), 1) median
from
    t
where
    rk1 >= s / 2
    and rk2 >= s / 2;