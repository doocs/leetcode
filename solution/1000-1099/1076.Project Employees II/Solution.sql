# Write your MySQL query statement below
SELECT project_id
FROM Project
GROUP BY 1
HAVING
    COUNT(1) >= ALL (
        SELECT COUNT(1)
        FROM Project
        GROUP BY project_id
    );
