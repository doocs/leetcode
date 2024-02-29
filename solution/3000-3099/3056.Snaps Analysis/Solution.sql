# Write your MySQL query statement below
SELECT
    age_bucket,
    ROUND(100 * SUM(IF(activity_type = 'send', time_spent, 0)) / SUM(time_spent), 2) AS send_perc,
    ROUND(100 * SUM(IF(activity_type = 'open', time_spent, 0)) / SUM(time_spent), 2) AS open_perc
FROM
    Activities
    JOIN Age USING (user_id)
GROUP BY 1;
