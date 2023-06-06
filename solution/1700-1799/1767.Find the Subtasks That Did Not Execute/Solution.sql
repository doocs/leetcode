# Write your MySQL query statement below
with recursive t(task_id, subtask_id) as (
    select
        task_id,
        subtasks_count
    from
        Tasks
    union
    all
    select
        task_id,
        subtask_id - 1
    from
        t
    where
        subtask_id >= 2
)
select
    t.*
from
    t
    left join Executed e using(task_id, subtask_id)
where
    e.subtask_id is null