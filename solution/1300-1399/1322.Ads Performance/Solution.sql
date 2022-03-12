# Write your MySQL query statement below
SELECT
  ad_id,
  Ifnull(ROUND(AVG(CASE
    WHEN action = 'Clicked' THEN 1
    WHEN action = 'Viewed' THEN 0
    ELSE NULL
  END) * 100, 2), 0) AS ctr
FROM ads
GROUP BY ad_id
ORDER BY ctr DESC,
ad_id ASC;
