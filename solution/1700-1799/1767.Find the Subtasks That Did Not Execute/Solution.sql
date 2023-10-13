# Write your MySQL query statement below
WITH RECURSIVE
    T(task_id, subtask_id) AS (
        SELECT
            task_id,
            subtasks_count
        FROM Tasks
        UNION ALL
        SELECT
            task_id,
            subtask_id - 1
        FROM t
        WHERE subtask_id > 1
    )
SELECT
    T.*
FROM
    T
    LEFT JOIN Executed USING (task_id, subtask_id)
WHERE Executed.subtask_id IS NULL;
