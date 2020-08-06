select distinct(Num) as ConsecutiveNums from Logs Curr where
    Num = (select Num from Logs where id = Curr.id - 1) and
    Num = (select Num from Logs where id = Curr.id - 2)
