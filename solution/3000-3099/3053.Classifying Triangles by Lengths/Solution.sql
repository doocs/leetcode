# Write your MySQL query statement below
SELECT
    CASE
        WHEN A + B <= C
        OR A + C <= B
        OR B + C <= A THEN 'Not A Triangle'
        WHEN A = B
        AND B = c THEN 'Equilateral'
        WHEN (A = B) + (B = C) + (A = C) = 1 THEN 'Isosceles'
        ELSE 'Scalene'
    END AS triangle_type
FROM Triangles;
