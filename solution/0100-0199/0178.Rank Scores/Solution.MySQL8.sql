# Write your MySQL query statement below
SELECT Score, DENSE_RANK() OVER (ORDER BY Score DESC) 'Rank'
FROM Scores;