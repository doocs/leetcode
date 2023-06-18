# Write your MySQL query statement below
SELECT
    state AS period_state,
    min(dt) AS start_date,
    max(dt) AS end_date
FROM
    (
        SELECT
            *,
            subdate(
                dt,
                rank() OVER (
                    PARTITION BY state
                    ORDER BY dt
                )
            ) AS dif
        FROM
            (
                SELECT
                    'failed' AS state,
                    fail_date AS dt
                FROM failed
                WHERE fail_date BETWEEN '2019-01-01' AND '2019-12-31'
                UNION ALL
                SELECT
                    'succeeded' AS state,
                    success_date AS dt
                FROM succeeded
                WHERE success_date BETWEEN '2019-01-01' AND '2019-12-31'
            ) AS t1
    ) AS t2
GROUP BY state, dif
ORDER BY dt;
