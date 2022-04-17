SELECT question_id AS survey_log
FROM   survey_log
GROUP  BY 1
ORDER  BY SUM(action = 'answer') / SUM(action = 'show') DESC
LIMIT  1; 
