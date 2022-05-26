SELECT
	s1.id,
	COALESCE ( s2.student, s1.student ) AS student 
FROM
	seat s1
	LEFT JOIN seat s2 ON ( s1.id + 1 ) ^ 1 - 1 = s2.id 
ORDER BY
	s1.id;