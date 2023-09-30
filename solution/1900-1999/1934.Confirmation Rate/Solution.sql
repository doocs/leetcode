# Write your MySQL query statement below
SELECT
    user_id,
    round(sum(if(action = 'confirmed', 1, 0)) / count(1), 2) AS confirmation_rate
FROM
    Signups
    LEFT JOIN Confirmations USING (user_id)
GROUP BY user_id;
