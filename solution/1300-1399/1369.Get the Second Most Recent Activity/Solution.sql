SELECT
    username,
    activity,
    startdate,
    enddate
FROM
    (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY username
                ORDER BY startdate DESC
            ) AS rk,
            COUNT(username) OVER (PARTITION BY username) AS cnt
        FROM UserActivity
    ) AS a
WHERE a.rk = 2 OR a.cnt = 1;
