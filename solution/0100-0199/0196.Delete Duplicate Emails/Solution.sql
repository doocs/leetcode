delete from Person where Id not in (select min(Id) from (select * from Person) as p group by p.Email)
