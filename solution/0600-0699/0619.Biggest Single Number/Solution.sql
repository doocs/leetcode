SELECT MAX(a.num) AS num
FROM (
	SELECT num
	FROM MyNumbers
	GROUP BY num
	HAVING count(*) = 1
) a;
