# Write your MySQL query statement below
SELECT 'bull' AS word, count(*) AS count
FROM Files
WHERE content LIKE '% bull %'
UNION
SELECT 'bear' AS word, count(*) AS count
FROM Files
WHERE content LIKE '% bear %';
