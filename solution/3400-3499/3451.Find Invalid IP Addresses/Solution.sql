SELECT
    ip,
    COUNT(*) AS invalid_count
FROM logs
WHERE
    LENGTH(ip) - LENGTH(REPLACE(ip, '.', '')) != 3

    OR SUBSTRING_INDEX(ip, '.', 1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) REGEXP '^0[0-9]'
    OR SUBSTRING_INDEX(ip, '.', -1) REGEXP '^0[0-9]'

    OR SUBSTRING_INDEX(ip, '.', 1) > 255
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 2), '.', -1) > 255
    OR SUBSTRING_INDEX(SUBSTRING_INDEX(ip, '.', 3), '.', -1) > 255
    OR SUBSTRING_INDEX(ip, '.', -1) > 255

GROUP BY 1
ORDER BY 2 DESC, 1 DESC;
