# Write your MySQL query statement below
select
    player_id,
    player_name,
    sum(
        (
            case
                when Wimbledon = player_id then 1
                else 0
            end
        ) + (
            case
                when Fr_open = player_id then 1
                else 0
            end
        ) + (
            case
                when US_open = player_id then 1
                else 0
            end
        ) + (
            case
                when Au_open = player_id then 1
                else 0
            end
        )
    ) grand_slams_count
from
    Championships
    cross join Players
group by
    player_id
having
    grand_slams_count > 0;