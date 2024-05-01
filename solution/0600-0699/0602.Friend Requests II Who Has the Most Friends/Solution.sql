# Write your MySQL query statement below
WITH
    T AS (
        SELECT requester_id, accepter_id FROM RequestAccepted
        UNION ALL
        SELECT accepter_id, requester_id FROM RequestAccepted
    )
SELECT requester_id AS id, COUNT(1) AS num
FROM T
GROUP BY 1
ORDER BY 2 DESC
LIMIT 1;
