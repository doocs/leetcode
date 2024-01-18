# Write your MySQL query statement below
WITH t AS
    (SELECT *,
		 SUM(id) over(partition by id
    ORDER BY  login_date range interval 4 day preceding)/id cnt
    FROM
        (SELECT DISTINCT *
        FROM Accounts
        JOIN Logins using(id) ) tt )
    SELECT DISTINCT id,
		 name
FROM t
WHERE cnt=5;
