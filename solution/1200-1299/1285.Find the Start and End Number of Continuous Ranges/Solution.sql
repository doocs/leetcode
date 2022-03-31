SELECT
  MIN(log_id) AS start_id,
  MAX(log_id) AS end_id
FROM (SELECT
  log_id,
  log_id - ROW_NUMBER() OVER (ORDER BY log_id) AS rk
FROM Logs) t
GROUP BY rk;
