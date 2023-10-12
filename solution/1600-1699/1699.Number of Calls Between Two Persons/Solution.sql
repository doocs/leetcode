# Write your MySQL query statement below
SELECT
    least(from_id, to_id) AS person1,
    greatest(from_id, to_id) AS person2,
    count(1) AS call_count,
    sum(duration) AS total_duration
FROM Calls
GROUP BY 1, 2;
