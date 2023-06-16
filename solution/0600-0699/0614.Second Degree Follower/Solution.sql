# Write your MySQL query statement below
SELECT
    f1.follower,
    COUNT(DISTINCT f2.follower) AS num
FROM
    follow AS f1
    JOIN follow AS f2 ON f1.follower = f2.followee
GROUP BY f1.follower
ORDER BY f1.follower;
