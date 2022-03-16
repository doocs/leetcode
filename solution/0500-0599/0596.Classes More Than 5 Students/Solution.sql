SELECT
	class 
FROM
	courses 
GROUP BY
	class 
HAVING
	COUNT( class ) >= 5