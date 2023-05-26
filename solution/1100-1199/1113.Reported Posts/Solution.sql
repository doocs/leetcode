# Write your MySQL query statement below
SELECT
	extra report_reason,
	count(
	DISTINCT ( post_id )) report_count 
FROM
	Actions 
WHERE
	action_date = '2019-07-04' 
	AND action = 'report' 
GROUP BY
	extra