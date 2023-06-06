# Write your MySQL query statement below
with t as (
    select
        *,
        rank() over(
            partition by exam_id
            order by
                score desc
        ) rk1,
        rank() over(
            partition by exam_id
            order by
                score asc
        ) rk2
    from
        Exam
)
select
    t.student_id,
    student_name
from
    t
    join Student s on t.student_id = s.student_id
group by
    t.student_id
having
    sum(rk1 = 1) = 0
    and sum(rk2 = 1) = 0;