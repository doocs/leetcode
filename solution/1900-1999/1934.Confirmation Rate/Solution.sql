# Write your MySQL query statement below
SELECT
    user_id,
    ROUND(SUM(IF(action = 'confirmed', 1, 0)) / COUNT(1), 2) AS confirmation_rate
FROM
    Signups
    LEFT JOIN Confirmations USING (user_id)
GROUP BY user_id;
