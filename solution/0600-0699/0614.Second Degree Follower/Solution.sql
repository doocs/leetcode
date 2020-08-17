SELECT followee AS follower,
        count(distinct(follower)) AS num
FROM follow
WHERE followee IN
    (SELECT follower
    FROM follow)
GROUP BY  followee
ORDER BY  followee
