# Write your MySQL query statement below
WITH
    T AS (
        SELECT candidate, SUM(vote) AS tot
        FROM
            (
                SELECT
                    candidate,
                    1 / (COUNT(candidate) OVER (PARTITION BY voter)) AS vote
                FROM Votes
                WHERE candidate IS NOT NULL
            ) AS t
        GROUP BY 1
    ),
    P AS (
        SELECT
            candidate,
            RANK() OVER (ORDER BY tot DESC) AS rk
        FROM T
    )
SELECT candidate
FROM P
WHERE rk = 1
ORDER BY 1;
