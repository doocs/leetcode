SELECT 
    ids AS id, COUNT(*) num
FROM
    (SELECT 
        requester_id AS ids
    FROM
        RequestAccepted UNION ALL SELECT 
        accepter_id
    FROM
        RequestAccepted) t
GROUP BY ids
ORDER BY num DESC
LIMIT 1;

