# Write your MySQL query statement below
with t as (
    select
        *,
        row_number() over(
            partition by continent
            order by
                name
        ) rn
    from
        Student
)
select
    max(if(continent = 'America', name, null)) America,
    max(if(continent = 'Asia', name, null)) Asia,
    max(if(continent = 'Europe', name, null)) Europe
from
    t
group by
    rn