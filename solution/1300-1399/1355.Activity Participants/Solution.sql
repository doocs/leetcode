# Write your MySQL query statement below
WITH
    t AS (
        SELECT activity, COUNT(1) AS cnt
        FROM Friends
        GROUP BY activity
    )
SELECT activity
FROM t
WHERE cnt > (SELECT MIN(cnt) FROM t) AND cnt < (SELECT MAX(cnt) FROM t);
