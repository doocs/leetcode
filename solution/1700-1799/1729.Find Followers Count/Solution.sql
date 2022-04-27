SELECT
    user_id,
    count(1) AS followers_count
FROM
    Followers
GROUP BY
    user_id
ORDER BY
    user_id;