SELECT
	Department.NAME AS Department,
	Employee.NAME AS Employee,
	Salary
FROM
	Employee,
	Department
WHERE
	Employee.DepartmentId = Department.Id
	AND ( Employee.DepartmentId, Salary )
    IN (SELECT DepartmentId, max( Salary )
        FROM Employee
        GROUP BY DepartmentId )
