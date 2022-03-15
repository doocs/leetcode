SELECT c1.seat_id
FROM   Cinema c1,
       Cinema c2
WHERE  ( ( c1.seat_id = c2.seat_id + 1 )
          OR ( c1.seat_id = c2.seat_id - 1 ) )
       AND ( c1.free = 1
             AND c2.free = 1 )
GROUP BY seat_id; 
