# Write your MySQL query statement below
SELECT
    machine_id,
    ROUND(AVG(IF(activity_type = 'start', -1, 1) * timestamp) * 2, 3) AS processing_time
FROM Activity
GROUP BY 1;
