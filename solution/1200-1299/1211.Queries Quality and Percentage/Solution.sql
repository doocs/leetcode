SELECT 
    IFNULL(query_name, 'null') AS query_name,
    ROUND(AVG(CAST(rating AS DECIMAL) / position), 2) AS quality,
    ROUND((SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END) / NULLIF(COUNT(*), 0)) * 100, 2) AS poor_query_percentage
FROM 
    Queries
GROUP BY 
    query_name WITH ROLLUP
HAVING 
    query_name IS NOT NULL
ORDER BY 
    query_name IS NULL, query_name;
