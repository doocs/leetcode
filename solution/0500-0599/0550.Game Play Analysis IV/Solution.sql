# Write your MySQL query statement below
SELECT  round( AVG( b.event_date IS NOT NULL ),2 ) fraction
FROM
(
	SELECT  player_id
	       ,MIN( event_date ) AS event_date
	FROM activity
	GROUP BY  player_id
) a
LEFT JOIN activity b
ON a.player_id = b.player_id AND DATEDIFF( a.event_date, b.event_date ) = -1