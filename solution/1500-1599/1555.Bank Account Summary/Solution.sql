# Write your MySQL query statement below
SELECT
    t.user_id,
    user_name,
    SUM(t.credit) AS credit,
    IF(SUM(t.credit) < 0, 'Yes', 'No') AS credit_limit_breached
FROM
    (
        SELECT paid_by AS user_id, -amount AS credit FROM Transactions
        UNION ALL
        SELECT paid_to AS user_id, amount AS credit FROM Transactions
        UNION ALL
        SELECT user_id, credit FROM Users
    ) AS t
    JOIN Users AS u ON t.user_id = u.user_id
GROUP BY t.user_id;
