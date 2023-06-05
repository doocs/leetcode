# Write your MySQL query statement below
select
    transaction_id
from
    (
        select
            transaction_id,
            rank() over(
                partition by date_format(day, '%Y-%m-%d')
                order by
                    amount desc
            ) rk
        from
            Transactions
        order by
            transaction_id
    ) t
where
    rk = 1