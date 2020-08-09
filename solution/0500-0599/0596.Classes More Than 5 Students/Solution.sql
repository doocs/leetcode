select
    c.class
from
    (select distinct courses.student, courses.class from courses) c
group by
    c.class
having
    count(c.class)>=5
