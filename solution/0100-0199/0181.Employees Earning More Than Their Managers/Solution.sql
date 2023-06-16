SELECT Name AS Employee
FROM Employee AS Curr
WHERE
    Salary > (
        SELECT Salary
        FROM Employee
        WHERE Id = Curr.ManagerId
    );
