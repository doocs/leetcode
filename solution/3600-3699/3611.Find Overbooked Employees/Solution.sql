# Write your MySQL query statement below
WITH
    week_meeting_hours AS (
        SELECT
            employee_id,
            YEAR(meeting_date) AS year,
            WEEK(meeting_date, 1) AS week,
            SUM(duration_hours) hours
        FROM meetings
        GROUP BY 1, 2, 3
    ),
    intensive_weeks AS (
        SELECT
            employee_id,
            employee_name,
            department,
            count(1) AS meeting_heavy_weeks
        FROM
            week_meeting_hours
            JOIN employees USING (employee_id)
        WHERE hours >= 20
        GROUP BY 1
    )
SELECT employee_id, employee_name, department, meeting_heavy_weeks
FROM intensive_weeks
WHERE meeting_heavy_weeks >= 2
ORDER BY 4 DESC, 2;
