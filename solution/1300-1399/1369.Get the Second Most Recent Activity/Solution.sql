SELECT
  username,
  activity,
  startdate,
  enddate
FROM (SELECT
  *,
  RANK() OVER (PARTITION BY username ORDER BY startdate DESC) rk,
  COUNT(username) OVER (PARTITION BY username) AS cnt
FROM UserActivity) a
WHERE a.rk = 2
OR a.cnt = 1;
