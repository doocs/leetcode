# Write your MySQL query statement below
WITH
    t AS (
        SELECT activity, count(1) AS cnt
        FROM Friends
        GROUP BY activity
    )
SELECT activity
FROM t
WHERE cnt > (SELECT min(cnt) FROM t) AND cnt < (SELECT max(cnt) FROM t);
