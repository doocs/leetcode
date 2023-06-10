# Write your MySQL query statement below
select
    country_name,
    case
        when avg(weather_state) <= 15 then 'Cold'
        when avg(weather_state) >= 25 then 'Hot'
        else 'Warm'
    end weather_type
from
    Weather w
    join Countries c on w.country_id = c.country_id
where
    date_format(day, '%Y-%m') = '2019-11'
group by
    w.country_id