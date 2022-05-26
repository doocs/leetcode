(SELECT ifnull(round(count(*) /
    (SELECT count(*)
    FROM
        (SELECT count(*)
        FROM friend_request
        GROUP BY  sender_id,send_to_id) request),2),0.0) AS accept_rate
        FROM
            (SELECT count(*)
            FROM request_accepted
            GROUP BY  requester_id,accepter_id) accepter)
