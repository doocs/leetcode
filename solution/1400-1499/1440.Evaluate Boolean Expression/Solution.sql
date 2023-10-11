# Write your MySQL query statement below
SELECT
    left_operand,
    operator,
    right_operand,
    CASE
        WHEN (
            (operator = '=' AND v1.value = v2.value)
            OR (operator = '>' AND v1.value > v2.value)
            OR (operator = '<' AND v1.value < v2.value)
        ) THEN 'true'
        ELSE 'false'
    END AS value
FROM
    Expressions AS e
    JOIN Variables AS v1 ON e.left_operand = v1.name
    JOIN Variables AS v2 ON e.right_operand = v2.name;
