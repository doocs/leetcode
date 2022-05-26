# Write your MySQL query statement below
SELECT
    Name
FROM
    (
        SELECT
            CandidateId AS id
        FROM
            Vote
        GROUP BY
            CandidateId
        ORDER BY
            COUNT(id) DESC
        LIMIT 1
    ) AS t
INNER JOIN
    Candidate c
ON
    t.id = c.id;