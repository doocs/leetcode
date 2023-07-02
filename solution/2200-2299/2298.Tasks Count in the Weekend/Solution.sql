# Write your MySQL query statement below
SELECT
    sum(weekday(submit_date) IN (5, 6)) AS weekend_cnt,
    sum(weekday(submit_date) NOT IN (5, 6)) AS working_cnt
FROM Tasks;
