# Write your MySQL query statement below
with recursive t as (
    select
        1 as n
    union
    all
    select
        n + 1
    from
        t
    where
        n < 100
)
select
    n ids
from
    t
where
    n < (
        select
            max(customer_id)
        from
            Customers
    )
    and n not in (
        select
            customer_id
        from
            Customers
    )