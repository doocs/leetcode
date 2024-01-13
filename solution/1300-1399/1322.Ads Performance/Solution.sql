SELECT
    ad_id,
    ROUND(IFNULL(SUM(action = 'Clicked') / SUM(action IN ('Clicked', 'Viewed')) * 100, 0), 2) AS ctr
FROM Ads
GROUP BY 1
ORDER BY 2 DESC, 1;
