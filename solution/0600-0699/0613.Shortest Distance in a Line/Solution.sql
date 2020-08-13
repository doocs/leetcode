SELECT min(abs(p1.x-p2.x)) shortest
FROM point p1, point p2
WHERE p1.x != p2.x
