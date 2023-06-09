# Write your MySQL query statement below
with t as (
    select
        tiv_2016,
        count(pid) over(partition by tiv_2015) as cnt1,
        count(pid) over(partition by concat(lat, lon)) as cnt2
    from
        Insurance
)
select
    round(sum(TIV_2016), 2) tiv_2016
from
    t
where
    cnt1 != 1
    and cnt2 = 1;