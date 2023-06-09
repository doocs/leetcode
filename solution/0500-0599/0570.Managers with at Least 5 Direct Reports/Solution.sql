# Write your MySQL query statement below
select
    name
from
    Employee e1
    join (
        select
            managerId
        from
            Employee
        where
            managerId is not null
        group by
            managerId
        having
            count(1) >= 5
    ) e2 on e1.id = e2.managerId;