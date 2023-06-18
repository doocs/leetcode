# Write your MySQL query statement below
SELECT
    round(avg(avg_per * 100), 2) AS average_daily_percent
FROM
    (
        SELECT
            count(DISTINCT t2.post_id) / count(DISTINCT t1.post_id) AS avg_per
        FROM
            Actions AS t1
            LEFT JOIN Removals AS t2 ON t1.post_id = t2.post_id
        WHERE t1.extra = 'spam'
        GROUP BY action_date
    ) AS t3;
