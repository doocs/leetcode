# Write your MySQL query statement below
with s as (
    select
        *
    from
        Calls
    union
    all
    select
        recipient_id,
        caller_id,
        call_time
    from
        Calls
),
t as (
    select
        caller_id user_id,
        first_value(recipient_id) over(
            partition by date_format(call_time, '%Y-%m-%d'),
            caller_id
            order by
                call_time asc
        ) first,
        first_value(recipient_id) over(
            partition by date_format(call_time, '%Y-%m-%d'),
            caller_id
            order by
                call_time desc
        ) last
    from
        s
)
select
    distinct user_id
from
    t
where
    first = last