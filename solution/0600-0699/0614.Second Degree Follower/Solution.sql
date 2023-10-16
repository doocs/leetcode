# Write your MySQL query statement below
WITH
    T AS (
        SELECT f1.follower AS follower, f2.follower AS followee
        FROM
            Follow AS f1
            JOIN Follow AS f2 ON f1.follower = f2.followee
    )
SELECT follower, COUNT(DISTINCT followee) AS num
FROM T
GROUP BY 1
ORDER BY 1;
