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
    'Senior' experience,
    count(employee_id) accepted_candidates
from
    s
where
    cur <= 70000
union
all
select
    'Junior' experience,
    count(employee_id) accepted_candidates
from
    j
where
    cur <= 70000;