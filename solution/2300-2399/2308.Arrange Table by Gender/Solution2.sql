SELECT
    user_id,
    gender
FROM Genders
ORDER BY
    (
        RANK() OVER (
            PARTITION BY gender
            ORDER BY user_id
        )
    ),
    2;
