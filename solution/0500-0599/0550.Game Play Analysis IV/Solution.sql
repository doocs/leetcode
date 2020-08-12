(select round(count(distinct (b.player_id)) / (select count(distinct (player_id)) from Activity), 2) as fraction
 from Activity a
          left join (select player_id, min(event_date) as first_date from Activity group by player_id) as b
                    on a.player_id = b.player_id and datediff(a.event_date, b.first_date) = 1)
