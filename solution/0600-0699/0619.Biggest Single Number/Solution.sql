SELECT
  MAX(a.num) nums
FROM (SELECT
  num,
  COUNT(*)
FROM mynumbers
GROUP BY num
HAVING COUNT(*) = 1) a;
