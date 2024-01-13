# Write your MySQL query statement below
SELECT
    RANK() OVER (ORDER BY (id - 1) ^ 1) AS id,
    student
FROM Seat;
