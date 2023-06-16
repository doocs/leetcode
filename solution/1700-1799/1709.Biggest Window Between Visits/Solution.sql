# Write your MySQL query statement below
SELECT
    user_id,
    max(datediff(nxt_day, visit_date)) AS biggest_window
FROM
    (
        SELECT
            user_id,
            visit_date,
            lead(visit_date, 1, '2021-1-1') OVER (
                PARTITION BY user_id
                ORDER BY visit_date
            ) AS nxt_day
        FROM UserVisits
    ) AS t
GROUP BY user_id
ORDER BY user_id;
