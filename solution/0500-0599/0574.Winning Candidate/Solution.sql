# Write your MySQL query statement below
SELECT name
FROM
    Candidate AS c
    LEFT JOIN Vote AS v ON c.id = v.candidateId
GROUP BY c.id
ORDER BY count(1) DESC
LIMIT 1;
