# Write your MySQL query statement below
WITH
    s AS (
        SELECT
            *
        FROM Calls
        UNION ALL
        SELECT
            recipient_id,
            caller_id,
            call_time
        FROM Calls
    ),
    t AS (
        SELECT
            caller_id AS user_id,
            first_value(recipient_id) OVER (
                PARTITION BY date_format(call_time, '%Y-%m-%d'), caller_id
                ORDER BY call_time ASC
            ) AS first,
            first_value(recipient_id) OVER (
                PARTITION BY date_format(call_time, '%Y-%m-%d'), caller_id
                ORDER BY call_time DESC
            ) AS last
        FROM s
    )
SELECT DISTINCT
    user_id
FROM t
WHERE first = last;
