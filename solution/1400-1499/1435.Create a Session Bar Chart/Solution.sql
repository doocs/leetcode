SELECT '[0-5>' AS bin, COUNT(1) AS total FROM Sessions WHERE duration < 300
UNION
SELECT '[5-10>' AS bin, COUNT(1) AS total FROM Sessions WHERE 300 <= duration AND duration < 600
UNION
SELECT '[10-15>' AS bin, COUNT(1) AS total FROM Sessions WHERE 600 <= duration AND duration < 900
UNION
SELECT '15 or more' AS bin, COUNT(1) AS total FROM Sessions WHERE 900 <= duration;
