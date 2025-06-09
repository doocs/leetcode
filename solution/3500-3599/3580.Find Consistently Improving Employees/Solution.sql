WITH
    recent AS (
        SELECT
            employee_id,
            rating,
            review_date,
            ROW_NUMBER() OVER (
                PARTITION BY employee_id
                ORDER BY review_date DESC
            ) AS rn,
            LAG(rating) OVER (
                PARTITION BY employee_id
                ORDER BY review_date DESC
            ) AS prev_rating
        FROM performance_reviews
    ),
    deltas AS (
        SELECT
            employee_id,
            prev_rating - rating AS delta,
            rn
        FROM recent
        WHERE rn > 1 AND rn <= 3
    )
SELECT
    employee_id,
    name,
    SUM(delta) AS improvement_score
FROM
    deltas
    JOIN employees USING (employee_id)
GROUP BY 1
HAVING COUNT(*) = 2 AND MIN(delta) > 0
ORDER BY 3 DESC, 2;
