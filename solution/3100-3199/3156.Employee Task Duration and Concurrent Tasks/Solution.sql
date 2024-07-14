# Write your MySQL query statement below
WITH
    T AS (
        SELECT DISTINCT employee_id, start_time AS st
        FROM Tasks
        UNION DISTINCT
        SELECT DISTINCT employee_id, end_time AS st
        FROM Tasks
    ),
    P AS (
        SELECT
            *,
            LEAD(st) OVER (
                PARTITION BY employee_id
                ORDER BY st
            ) AS ed
        FROM T
    ),
    S AS (
        SELECT
            P.*,
            COUNT(1) AS concurrent_count
        FROM
            P
            INNER JOIN Tasks USING (employee_id)
        WHERE P.st >= Tasks.start_time AND P.ed <= Tasks.end_time
        GROUP BY 1, 2, 3
    )
SELECT
    employee_id,
    FLOOR(SUM(TIME_TO_SEC(TIMEDIFF(ed, st)) / 3600)) AS total_task_hours,
    MAX(concurrent_count) AS max_concurrent_tasks
FROM S
GROUP BY 1
ORDER BY 1;
