# Write your MySQL query statement below
SELECT activity_date AS day, COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE activity_date <= '2019-07-27' AND DATEDIFF('2019-07-27', activity_date) < 30
GROUP BY 1;
