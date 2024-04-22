WITH
    T AS (
        SELECT
            first_name,
            type,
            CONCAT(
                LPAD(duration DIV 3600, 2, '0'),
                ':',
                LPAD((duration MOD 3600) DIV 60, 2, '0'),
                ':',
                LPAD(duration MOD 60, 2, '0')
            ) AS duration_formatted,
            RANK() OVER (
                PARTITION BY type
                ORDER BY duration DESC
            ) AS rk
        FROM
            Calls AS c1
            JOIN Contacts AS c2 ON c1.contact_id = c2.id
    )
SELECT
    first_name,
    type,
    duration_formatted
FROM T
WHERE rk <= 3
ORDER BY 2, 3 DESC, 1 DESC;
