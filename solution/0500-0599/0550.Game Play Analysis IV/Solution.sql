# Write your MySQL query statement below
SELECT ROUND(AVG(b.event_date IS NOT NULL), 2) AS fraction
FROM
    (
        SELECT player_id, MIN(event_date) AS event_date
        FROM Activity
        GROUP BY 1
    ) AS a
    LEFT JOIN Activity AS b
        ON a.player_id = b.player_id AND DATEDIFF(a.event_date, b.event_date) = -1;
