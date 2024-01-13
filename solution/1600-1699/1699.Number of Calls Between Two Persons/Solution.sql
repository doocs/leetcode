# Write your MySQL query statement below
SELECT
    IF(from_id < to_id, from_id, to_id) AS person1,
    IF(from_id < to_id, to_id, from_id) AS person2,
    COUNT(1) AS call_count,
    SUM(duration) AS total_duration
FROM Calls
GROUP BY 1, 2;
