# Write your MySQL query statement below
SELECT DISTINCT
    t1.N AS N,
    IF(t1.P IS NULL, 'Root', IF(t2.P IS NULL, 'Leaf', 'Inner')) AS Type
FROM
    Tree AS t1
    LEFT JOIN Tree AS t2 ON t1.N = t2.p
ORDER BY 1;
