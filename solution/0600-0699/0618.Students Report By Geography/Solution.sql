SELECT MAX(CASE
		WHEN continent = 'America' THEN name
	END) AS America, MAX(CASE
		WHEN continent = 'Asia' THEN name
	END) AS Asia
	, MAX(CASE
		WHEN continent = 'Europe' THEN name
	END) AS Europe
FROM (
	SELECT name, continent, row_number() OVER (PARTITION BY continent ORDER BY name) AS rk
	FROM student
) t
GROUP BY rk
