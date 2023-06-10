# Write your MySQL query statement below
with t as (
    select
        r1.user_id user1_id,
        r2.user_id user2_id,
        rank() over(
            order by
                count(1) desc
        ) rk
    from
        Relations r1
        join Relations r2 on r1.follower_id = r2.follower_id
        and r1.user_id < r2.user_id
    group by
        r1.user_id,
        r2.user_id
)
select
    user1_id,
    user2_id
from
    t
where
    rk = 1;