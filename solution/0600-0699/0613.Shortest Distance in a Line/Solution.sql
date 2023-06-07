# Write your MySQL query statement below
select
    min(abs(p1.x - p2.x)) as shortest
from
    Point p1
    join Point p2 on p1.x != p2.x;