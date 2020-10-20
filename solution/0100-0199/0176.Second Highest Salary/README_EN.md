# [176. Second Highest Salary](https://leetcode.com/problems/second-highest-salary)

[中文文档](/solution/0100-0199/0176.Second%20Highest%20Salary/README.md)

## Description

<p>Write a SQL query to get the second highest salary from the <code>Employee</code> table.</p>

<pre>

+----+--------+

| Id | Salary |

+----+--------+

| 1  | 100    |

| 2  | 200    |

| 3  | 300    |

+----+--------+

</pre>

<p>For example, given the above Employee table, the query should return <code>200</code> as the second highest salary. If there is no second highest salary, then the query should return <code>null</code>.</p>

<pre>

+---------------------+

| SecondHighestSalary |

+---------------------+

| 200                 |

+---------------------+

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```
select (select distinct Salary from Employee order by Salary desc limit 1 offset 1) as
SecondHighestSalary;
```

<!-- tabs:end -->
