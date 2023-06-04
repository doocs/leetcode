# Write your MySQL query statement below
select
    distinct a.account_id
from
    LogInfo a
    join LogInfo b on a.account_id = b.account_id
    and a.ip_address != b.ip_address
    and a.login between b.login
    and b.logout;