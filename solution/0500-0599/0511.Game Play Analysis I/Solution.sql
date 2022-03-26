SELECT 
    player_id, MIN(event_date) first_login
FROM
    Activity
GROUP BY player_id;
