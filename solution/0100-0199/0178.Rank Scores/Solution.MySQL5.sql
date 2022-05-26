SELECT Score,
       CONVERT(rk, SIGNED) `Rank`
FROM (SELECT Score,
             IF(@latest = Score, @rank, @rank := @rank + 1) rk,
             @latest := Score
      FROM Scores,
           (SELECT @rank := 0, @latest := NULL) tmp
      ORDER BY Score DESC) s;
