# Write your MySQL query statement below
WITH RECURSIVE
    t(task_id, subtask_id) AS (
        SELECT
            task_id,
            subtasks_count
        FROM Tasks
        UNION ALL
        SELECT
            task_id,
            subtask_id - 1
        FROM t
        WHERE subtask_id >= 2
    )
SELECT
    t.*
FROM
    t
    LEFT JOIN Executed AS e USING (task_id, subtask_id)
WHERE e.subtask_id IS NULL;
