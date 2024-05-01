# Write your MySQL query statement below
WITH
    base AS (
        SELECT requester_id AS id FROM RequestAccepted
        UNION ALL
        SELECT accepter_id AS id FROM RequestAccepted
    )
SELECT id, count(*) AS num
FROM base
GROUP BY 1
ORDER BY 2 DESC
LIMIT 1;
