# Write your MySQL query statement below
SELECT  a.person_name
FROM Queue a, Queue b
WHERE a.turn >= b.turn
GROUP BY  a.person_id
HAVING SUM(b.weight) <= 1000
ORDER BY a.turn DESC
LIMIT 1