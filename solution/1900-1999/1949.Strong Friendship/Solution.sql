# Write your MySQL query statement below
with t as (
    select
        *
    from
        Friendship
    union
    all
    select
        user2_id,
        user1_id
    from
        Friendship
)
select
    t1.user1_id,
    t1.user2_id,
    count(1) common_friend
from
    t t1
    join t t2 on t1.user2_id = t2.user1_id
    join t t3 on t1.user1_id = t3.user1_id
where
    t3.user2_id = t2.user2_id
    and t1.user1_id < t1.user2_id
group by
    t1.user1_id,
    t1.user2_id
having
    count(1) >= 3;