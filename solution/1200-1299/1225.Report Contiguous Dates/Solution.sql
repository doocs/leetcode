# Write your MySQL query statement below
select
    state as period_state,
    min(dt) as start_date,
    max(dt) as end_date
from
    (
        select
            *,
            subdate(
                dt,
                rank() over(
                    partition by state
                    order by
                        dt
                )
            ) as dif
        from
            (
                select
                    'failed' as state,
                    fail_date as dt
                from
                    failed
                where
                    fail_date between '2019-01-01'
                    and '2019-12-31'
                union
                all
                select
                    'succeeded' as state,
                    success_date as dt
                from
                    succeeded
                where
                    success_date between '2019-01-01'
                    and '2019-12-31'
            ) t1
    ) t2
group by
    state,
    dif
order by
    dt