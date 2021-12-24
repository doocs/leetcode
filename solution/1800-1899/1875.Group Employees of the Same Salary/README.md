# [1875. Group Employees of the Same Salary](https://leetcode-cn.com/problems/group-employees-of-the-same-salary)

[English Version](/solution/1800-1899/1875.Group%20Employees%20of%20the%20Same%20Salary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Employees</code></p>

<pre>

+-------------+---------+

| Column Name | Type    |

+-------------+---------+

| employee_id | int     |

| name        | varchar |

| salary      | int     |

+-------------+---------+

employee_id is the primary key for this table.

Each row of this table indicates the employee ID, employee name, and salary.

</pre>

<p>&nbsp;</p>

<p>A company wants to divide the employees into teams such that all the members on each team have the <strong>same salary</strong>. The teams should follow these criteria:</p>

<ul>
	<li>Each team should consist of <strong>at least two</strong> employees.</li>
	<li>All the employees on a team should have the <strong>same salary</strong>.</li>
	<li>All the employees of the same salary should be assigned to the same team.</li>
	<li>If the salary of an employee is unique, we <strong>do not</strong> assign this employee to any team.</li>
	<li>A team&#39;s ID is assigned based on the <strong>rank of the team&#39;s salary</strong> relative to the other teams&#39; salaries, where the team with the <strong>lowest</strong> salary has <code>team_id = 1</code>. Note that the salaries for employees not on a team are <strong>not included</strong> in this ranking.</li>
</ul>

<p>Write an SQL query to get the <code>team_id</code> of each employee that is in a team.</p>

<p>Return the result table ordered by <code>team_id</code> <strong>in ascending order</strong>. In case of a tie, order it by&nbsp;<code>employee_id</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example:</p>

<p>&nbsp;</p>

<pre>

Employees table:

+-------------+---------+--------+

| employee_id | name    | salary |

+-------------+---------+--------+

| 2           | Meir    | 3000   |

| 3           | Michael | 3000   |

| 7           | Addilyn | 7400   |

| 8           | Juan    | 6100   |

| 9           | Kannon  | 7400   |

+-------------+---------+--------+



Result table:

+-------------+---------+--------+---------+

| employee_id | name    | salary | team_id |

+-------------+---------+--------+---------+

| 2           | Meir    | 3000   | 1       |

| 3           | Michael | 3000   | 1       |

| 7           | Addilyn | 7400   | 2       |

| 9           | Kannon  | 7400   | 2       |

+-------------+---------+--------+---------+



Meir (employee_id=2) and Michael (employee_id=3) are in the same team because they have the same salary of 3000.

Addilyn (employee_id=7) and Kannon (employee_id=9) are in the same team because they have the same salary of 7400.

Juan (employee_id=8) is not included in any team because their salary of 6100 is unique (i.e. no other employee has the same salary).

The team IDs are assigned as follows (based on salary ranking, lowest first):

- team_id=1: Meir and Michael, salary of 3000

- team_id=2: Addilyn and Kannon, salary of 7400

Juan&#39;s salary of 6100 is not included in the ranking because they are not on a team.</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
