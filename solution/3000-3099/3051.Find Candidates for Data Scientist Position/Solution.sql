# Write your MySQL query statement below
SELECT candidate_id
FROM Candidates
WHERE skill IN ('Python', 'Tableau', 'PostgreSQL')
GROUP BY 1
HAVING COUNT(1) = 3
ORDER BY 1;
