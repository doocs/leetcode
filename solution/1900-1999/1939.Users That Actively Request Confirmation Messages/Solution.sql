SELECT DISTINCT
    c1.user_id AS user_id
FROM
    Confirmations AS c1
    INNER JOIN Confirmations AS c2 ON c1.user_id = c2.user_id
WHERE
    c1.time_stamp < c2.time_stamp
    AND TIMESTAMPDIFF(SECOND, c1.time_stamp, c2.time_stamp) <= 24 * 60 * 60;
