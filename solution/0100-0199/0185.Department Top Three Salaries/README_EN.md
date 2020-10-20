# [185. Department Top Three Salaries](https://leetcode.com/problems/department-top-three-salaries)

[中文文档](/solution/0100-0199/0185.Department%20Top%20Three%20Salaries/README.md)

## Description

<p>The <code>Employee</code> table holds all employees. Every employee has an Id, and there is also a column for the department Id.</p>

<pre>

+----+-------+--------+--------------+

| Id | Name  | Salary | DepartmentId |

+----+-------+--------+--------------+

| 1  | Joe   | 85000  | 1            |

| 2  | Henry | 80000  | 2            |

| 3  | Sam   | 60000  | 2            |

| 4  | Max   | 90000  | 1            |

| 5  | Janet | 69000  | 1            |

| 6  | Randy | 85000  | 1            |

| 7  | Will  | 70000  | 1            |

+----+-------+--------+--------------+

</pre>

<p>The <code>Department</code> table holds all departments of the company.</p>

<pre>

+----+----------+

| Id | Name     |

+----+----------+

| 1  | IT       |

| 2  | Sales    |

+----+----------+

</pre>

<p>Write a SQL query to find employees who earn the top three salaries in each of the department. For the above tables, your SQL query should return the following rows (order of rows does not matter).</p>

<pre>

+------------+----------+--------+

| Department | Employee | Salary |

+------------+----------+--------+

| IT         | Max      | 90000  |

| IT         | Randy    | 85000  |

| IT         | Joe      | 85000  |

| IT         | Will     | 70000  |

| Sales      | Henry    | 80000  |

| Sales      | Sam      | 60000  |

+------------+----------+--------+

</pre>

<p><strong>Explanation:</strong></p>

<p>In IT department, Max earns the highest salary, both Randy and Joe earn the second highest salary, and Will earns the third highest salary. There are only two employees in the Sales department, Henry earns the highest salary while Sam earns the second highest salary.</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```
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
```

<!-- tabs:end -->
