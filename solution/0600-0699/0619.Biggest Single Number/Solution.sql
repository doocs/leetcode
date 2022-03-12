SELECT
  MAX(a.num) nums
FROM (SELECT
  num,
  COUNT(*)
FROM biggestsinglenumber
GROUP BY num
HAVING COUNT(*) = 1) a;
