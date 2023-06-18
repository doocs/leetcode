(
    SELECT
        '[0-5>' AS bin,
        SUM(
            CASE
                WHEN duration / 60 < 5 THEN 1
                ELSE 0
            END
        ) AS total
    FROM Sessions
)
UNION
(
    SELECT
        '[5-10>' AS bin,
        SUM(
            CASE
                WHEN (duration / 60 >= 5 AND duration / 60 < 10) THEN 1
                ELSE 0
            END
        ) AS total
    FROM Sessions
)
UNION
(
    SELECT
        '[10-15>' AS bin,
        SUM(
            CASE
                WHEN (duration / 60 >= 10 AND duration / 60 < 15) THEN 1
                ELSE 0
            END
        ) AS total
    FROM Sessions
)
UNION
(
    SELECT
        '15 or more' AS bin,
        SUM(
            CASE
                WHEN duration / 60 >= 15 THEN 1
                ELSE 0
            END
        ) AS total
    FROM Sessions
);
