# Write your MySQL query statement below
SELECT
    id,
    sum(
        CASE month
            WHEN 'Jan' THEN revenue
        END
    ) AS Jan_Revenue,
    sum(
        CASE month
            WHEN 'Feb' THEN revenue
        END
    ) AS Feb_Revenue,
    sum(
        CASE month
            WHEN 'Mar' THEN revenue
        END
    ) AS Mar_Revenue,
    sum(
        CASE month
            WHEN 'Apr' THEN revenue
        END
    ) AS Apr_Revenue,
    sum(
        CASE month
            WHEN 'May' THEN revenue
        END
    ) AS May_Revenue,
    sum(
        CASE month
            WHEN 'Jun' THEN revenue
        END
    ) AS Jun_Revenue,
    sum(
        CASE month
            WHEN 'Jul' THEN revenue
        END
    ) AS Jul_Revenue,
    sum(
        CASE month
            WHEN 'Aug' THEN revenue
        END
    ) AS Aug_Revenue,
    sum(
        CASE month
            WHEN 'Sep' THEN revenue
        END
    ) AS Sep_Revenue,
    sum(
        CASE month
            WHEN 'Oct' THEN revenue
        END
    ) AS Oct_Revenue,
    sum(
        CASE month
            WHEN 'Nov' THEN revenue
        END
    ) AS Nov_Revenue,
    sum(
        CASE month
            WHEN 'Dec' THEN revenue
        END
    ) AS Dec_Revenue
FROM Department
GROUP BY 1;
