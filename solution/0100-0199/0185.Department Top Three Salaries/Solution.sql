SELECT
	Department.NAME AS Department,
	Employee.NAME AS Employee,
	Salary
FROM
	Employee,
	Department
WHERE
	Employee.DepartmentId = Department.Id
	AND  (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > Employee.Salary
                AND Employee.DepartmentId = e2.DepartmentId
    ) < 3
