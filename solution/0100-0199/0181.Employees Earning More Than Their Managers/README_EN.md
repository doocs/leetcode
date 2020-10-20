# [181. Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers)

[中文文档](/solution/0100-0199/0181.Employees%20Earning%20More%20Than%20Their%20Managers/README.md)

## Description

<p>The <code>Employee</code> table holds all employees including their managers. Every employee has an Id, and there is also a column for the manager Id.</p>

<pre>

+----+-------+--------+-----------+

| Id | Name  | Salary | ManagerId |

+----+-------+--------+-----------+

| 1  | Joe   | 70000  | 3         |

| 2  | Henry | 80000  | 4         |

| 3  | Sam   | 60000  | NULL      |

| 4  | Max   | 90000  | NULL      |

+----+-------+--------+-----------+

</pre>

<p>Given the <code>Employee</code> table, write a SQL query that finds out employees who earn more than their managers. For the above table, Joe is the only employee who earns more than his manager.</p>

<pre>

+----------+

| Employee |

+----------+

| Joe      |

+----------+

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select Name as Employee from Employee Curr where
    Salary > (select Salary from Employee where Id = Curr.ManagerId)
```

<!-- tabs:end -->
