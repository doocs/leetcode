# Write your MySQL query statement below
SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON SUBDATE(w1.recordDate, 1) = w2.recordDate AND w1.temperature > w2.temperature;
