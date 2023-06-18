SELECT DISTINCT (Num) AS ConsecutiveNums
FROM Logs AS Curr
WHERE
    Num = (SELECT Num FROM Logs WHERE id = Curr.id - 1)
    AND Num = (SELECT Num FROM Logs WHERE id = Curr.id - 2);
