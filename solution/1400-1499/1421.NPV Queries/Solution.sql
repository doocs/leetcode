# Write your MySQL query statement below
SELECT q.*, ifnull(npv, 0) AS npv
FROM
    Queries AS q
    LEFT JOIN NPV AS n USING (id, year);
