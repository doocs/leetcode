# Write your MySQL query statement below
SELECT
    from_id AS person1,
    to_id AS person2,
    COUNT(1) AS call_count,
    SUM(duration) AS total_duration
FROM
    Calls
GROUP BY
    LEAST(from_id, to_id),
    GREATEST(from_id, to_id);