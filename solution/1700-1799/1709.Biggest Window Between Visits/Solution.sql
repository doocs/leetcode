# Write your MySQL query statement below
select
    user_id,
    max(datediff(nxt_day, visit_date)) as biggest_window
from
    (
        select
            user_id,
            visit_date,
            lead(visit_date, 1, '2021-1-1') over (
                partition by user_id
                order by
                    visit_date
            ) as nxt_day
        from
            UserVisits
    ) t
group by
    user_id
order by
    user_id;