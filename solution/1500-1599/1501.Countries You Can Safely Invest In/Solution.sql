# Write your MySQL query statement below
with t as (
    select
        left(phone_number, 3) as country_code,
        avg(duration) as duration
    from
        Person
        join Calls on id in (caller_id, callee_id)
    group by
        country_code
)
select
    c.name country
from
    Country c
    join t on c.country_code = t.country_code
where
    t.duration > (
        select
            avg(duration)
        from
            Calls
    )