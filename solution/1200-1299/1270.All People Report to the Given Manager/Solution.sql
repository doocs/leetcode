# Write your MySQL query statement below
SELECT e1.employee_id
FROM   employees e1
JOIN   employees e2
JOIN   employees e3
ON     e1.manager_id=e2.employee_id
AND    e2.manager_id=e3.employee_id
where  e3.manager_id=1
AND    e1.employee_id!=1;
