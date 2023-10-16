# Write your MySQL query statement below
SELECT
    contest_id,
    ROUND(COUNT(1) * 100 / (SELECT COUNT(1) FROM Users), 2) AS percentage
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id;
