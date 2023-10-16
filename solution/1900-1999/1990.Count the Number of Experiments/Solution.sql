# Write your MySQL query statement below
WITH
    P AS (
        SELECT 'Android' AS platform
        UNION
        SELECT 'IOS'
        UNION
        SELECT 'Web'
    ),
    Exp AS (
        SELECT 'Reading' AS experiment_name
        UNION
        SELECT 'Sports'
        UNION
        SELECT 'Programming'
    ),
    T AS (
        SELECT *
        FROM
            P,
            Exp
    )
SELECT platform, experiment_name, COUNT(experiment_id) AS num_experiments
FROM
    T AS t
    LEFT JOIN Experiments USING (platform, experiment_name)
GROUP BY 1, 2;
