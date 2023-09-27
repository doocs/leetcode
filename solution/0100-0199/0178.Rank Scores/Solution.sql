# Write your MySQL query statement below
SELECT
    score,
    dense_rank() OVER (ORDER BY score DESC) AS 'rank'
FROM Scores;
