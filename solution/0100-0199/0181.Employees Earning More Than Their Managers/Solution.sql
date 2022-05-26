select Name as Employee
from Employee Curr
where Salary > (
        select Salary
        from Employee
        where Id = Curr.ManagerId
    )