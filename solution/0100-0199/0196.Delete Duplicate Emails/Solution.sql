DELETE
FROM
	Person
WHERE
	Id NOT IN (
	SELECT
		MIN( Id )
	FROM
		( SELECT * FROM Person ) AS p
	GROUP BY
        p.Email
	);