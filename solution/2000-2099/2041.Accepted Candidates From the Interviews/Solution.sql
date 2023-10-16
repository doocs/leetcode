# Write your MySQL query statement below
SELECT candidate_id
FROM
    Candidates AS c
    LEFT JOIN Rounds AS r ON c.interview_id = r.interview_id
WHERE years_of_exp >= 2
GROUP BY c.interview_id
HAVING SUM(score) > 15;
