SELECT name
FROM customer
WHERE referee_id != 2
        OR referee_id is null
