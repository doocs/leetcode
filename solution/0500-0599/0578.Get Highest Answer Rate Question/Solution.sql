SELECT question_id AS survey_log
FROM   SurveyLog
GROUP  BY 1
ORDER  BY SUM(action = 'answer') / SUM(action = 'show') DESC
LIMIT  1; 
