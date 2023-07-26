# Write your MySQL query statement below
SELECT DISTINCT viewer_id AS id
FROM Views
GROUP BY viewer_id, view_date
HAVING count(DISTINCT article_id) > 1
ORDER BY 1;
