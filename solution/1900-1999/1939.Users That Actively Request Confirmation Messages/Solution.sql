SELECT DISTINCT
    c1.user_id AS user_id
FROM
    Confirmations AS c1
    JOIN Confirmations AS c2 USING (user_id)
WHERE
    c1.time_stamp < c2.time_stamp
    AND TIMESTAMPDIFF(SECOND, c1.time_stamp, c2.time_stamp) <= 24 * 60 * 60;
