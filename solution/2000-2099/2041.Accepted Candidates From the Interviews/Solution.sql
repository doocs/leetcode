# Write your MySQL query statement below
SELECT candidate_id
FROM
    Candidates
    JOIN Rounds USING (interview_id)
WHERE years_of_exp >= 2
GROUP BY 1
HAVING SUM(score) > 15;
