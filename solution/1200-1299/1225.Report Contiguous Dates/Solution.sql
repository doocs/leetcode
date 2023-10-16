# Write your MySQL query statement below
WITH
    T AS (
        SELECT fail_date AS dt, 'failed' AS st
        FROM Failed
        WHERE YEAR(fail_date) = 2019
        UNION ALL
        SELECT success_date AS dt, 'succeeded' AS st
        FROM Succeeded
        WHERE YEAR(success_date) = 2019
    )
SELECT
    st AS period_state,
    MIN(dt) AS start_date,
    MAX(dt) AS end_date
FROM
    (
        SELECT
            *,
            SUBDATE(
                dt,
                RANK() OVER (
                    PARTITION BY st
                    ORDER BY dt
                )
            ) AS pt
        FROM T
    ) AS t
GROUP BY 1, pt
ORDER BY 2;
