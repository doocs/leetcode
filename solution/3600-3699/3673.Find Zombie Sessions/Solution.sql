# Write your MySQL query statement below
SELECT
    session_id,
    user_id,
    TIMESTAMPDIFF(MINUTE, MIN(event_timestamp), MAX(event_timestamp)) session_duration_minutes,
    SUM(event_type = 'scroll') scroll_count
FROM app_events
GROUP BY session_id
HAVING
    session_duration_minutes >= 30
    AND SUM(event_type = 'click') / SUM(event_type = 'scroll') < 0.2
    AND SUM(event_type = 'purchase') = 0
    AND SUM(event_type = 'scroll') >= 5
ORDER BY scroll_count DESC, session_id;
