# Write your MySQL query statement below
WITH
    S AS (
        SELECT
            first_col,
            row_number() OVER (ORDER BY first_col) AS rk
        FROM Data
    ),
    T AS (
        SELECT
            second_col,
            row_number() OVER (ORDER BY second_col DESC) AS rk
        FROM Data
    )
SELECT first_col, second_col
FROM
    S
    JOIN T USING (rk);
