# Write your MySQL query statement below
SELECT
    CASE
        WHEN n1.cnt > n2.cnt THEN 'New York University'
        WHEN n1.cnt < n2.cnt THEN 'California University'
        ELSE 'No Winner' 
    END AS winner
FROM
    (SELECT count(1) cnt FROM NewYork WHERE score >= 90) n1,
    (SELECT COUNT(1) cnt FROM California WHERE score >= 90) n2;