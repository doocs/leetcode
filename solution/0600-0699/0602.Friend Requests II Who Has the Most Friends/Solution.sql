SELECT 
    ids AS id, COUNT(*) cnt
FROM
    (SELECT 
        requester_id AS ids
    FROM
        RequestAccepted UNION ALL SELECT 
        accepter_id
    FROM
        RequestAccepted) t
GROUP BY ids
ORDER BY cnt DESC
LIMIT 1;
