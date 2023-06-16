# Write your MySQL query statement below
SELECT DISTINCT
    (viewer_id) AS id
FROM Views
GROUP BY view_date, viewer_id
HAVING COUNT(DISTINCT article_id) > 1
ORDER BY id;
