select
    Score,
    (select count(*) from (select distinct Score s from Scores) tmp where s>=Score) Rank
from Scores order by Rank;
