# Write your MySQL query statement below
WITH
    s AS (
        SELECT DISTINCT spend_date, 'desktop' AS platform FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'mobile' AS platform FROM Spending
        UNION
        SELECT DISTINCT spend_date, 'both' AS platform FROM Spending
    ),
    t AS (
        SELECT
            user_id,
            spend_date,
            if(count(platform) = 2, 'both', platform) AS platform,
            sum(amount) AS amount
        FROM Spending
        GROUP BY 1, 2
    )
SELECT
    t1.*,
    ifnull(sum(amount), 0) AS total_amount,
    count(t2.user_id) AS total_users
FROM
    s AS t1
    LEFT JOIN t AS t2 USING (spend_date, platform)
GROUP BY 1, 2;
