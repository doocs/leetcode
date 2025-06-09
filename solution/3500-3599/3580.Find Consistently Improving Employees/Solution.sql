WITH
    recent AS (
        SELECT
            employee_id,
            review_date,
            ROW_NUMBER() OVER (
                PARTITION BY employee_id
                ORDER BY review_date DESC
            ) AS rn,
            (
                LAG(rating) OVER (
                    PARTITION BY employee_id
                    ORDER BY review_date DESC
                ) - rating
            ) AS delta
        FROM performance_reviews
    )
SELECT
    employee_id,
    name,
    SUM(delta) AS improvement_score
FROM
    recent
    JOIN employees USING (employee_id)
WHERE rn > 1 AND rn <= 3
GROUP BY 1
HAVING COUNT(*) = 2 AND MIN(delta) > 0
ORDER BY 3 DESC, 2;
