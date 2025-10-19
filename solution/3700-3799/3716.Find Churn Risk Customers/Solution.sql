WITH
    user_with_last_event AS (
        SELECT
            s.*,
            ROW_NUMBER() OVER (
                PARTITION BY user_id
                ORDER BY event_date DESC, event_id DESC
            ) AS rn
        FROM subscription_events s
    ),
    user_history AS (
        SELECT
            user_id,
            MIN(event_date) AS start_date,
            MAX(event_date) AS last_event_date,
            MAX(monthly_amount) AS max_historical_amount,
            SUM(
                CASE
                    WHEN event_type = 'downgrade' THEN 1
                    ELSE 0
                END
            ) AS downgrade_count
        FROM subscription_events
        GROUP BY user_id
    ),
    latest_event AS (
        SELECT
            user_id,
            event_type AS last_event_type,
            plan_name AS current_plan,
            monthly_amount AS current_monthly_amount
        FROM user_with_last_event
        WHERE rn = 1
    )
SELECT
    l.user_id,
    l.current_plan,
    l.current_monthly_amount,
    h.max_historical_amount,
    DATEDIFF(h.last_event_date, h.start_date) AS days_as_subscriber
FROM
    latest_event l
    JOIN user_history h ON l.user_id = h.user_id
WHERE
    l.last_event_type <> 'cancel'
    AND h.downgrade_count >= 1
    AND l.current_monthly_amount < 0.5 * h.max_historical_amount
    AND DATEDIFF(h.last_event_date, h.start_date) >= 60
ORDER BY days_as_subscriber DESC, l.user_id ASC;
