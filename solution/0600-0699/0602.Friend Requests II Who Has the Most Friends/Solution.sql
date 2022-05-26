SELECT id,
         count(*) num
FROM (
    (SELECT requester_id AS id
    FROM request_accepted )
    UNION
    all
        (SELECT accepter_id AS id
        FROM request_accepted) ) t
    GROUP BY  id
ORDER BY  num DESC limit 1
