# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            power,
            CASE power
                WHEN 0 THEN IF(factor > 0, concat('+', factor), factor)
                WHEN 1 THEN concat(
                    IF(factor > 0, concat('+', factor), factor),
                    'X'
                )
                ELSE concat(
                    IF(factor > 0, concat('+', factor), factor),
                    'X^',
                    power
                )
            END AS it
        FROM Terms
    )
SELECT
    concat(group_concat(it ORDER BY power DESC SEPARATOR ""), '=0') AS equation
FROM T;