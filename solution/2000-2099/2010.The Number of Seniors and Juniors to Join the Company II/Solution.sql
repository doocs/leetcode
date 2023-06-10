# Write your MySQL query statement below
with s as (
    select
        employee_id,
        sum(salary) over(
            order by
                salary
        ) cur
    from
        Candidates
    where
        experience = 'Senior'
),
j as (
    select
        employee_id,
        ifnull(
            (
                select
                    max(cur)
                from
                    s
                where
                    cur <= 70000
            ),
            0
        ) + sum(salary) over(
            order by
                salary
        ) cur
    from
        Candidates
    where
        experience = 'Junior'
)
select
    employee_id
from
    s
where
    cur <= 70000
union
select
    employee_id
from
    j
where
    cur <= 70000;