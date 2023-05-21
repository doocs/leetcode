# Write your MySQL query statement below
SELECT  DISTINCT a.seat_id
FROM Cinema a
JOIN Cinema b
ON ABS(a.seat_id - b.seat_id) = 1 AND a.free = true AND b.free = true
ORDER BY a.seat_id