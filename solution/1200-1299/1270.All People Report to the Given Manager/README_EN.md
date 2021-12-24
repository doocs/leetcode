# [1270. All People Report to the Given Manager](https://leetcode.com/problems/all-people-report-to-the-given-manager)

[中文文档](/solution/1200-1299/1270.All%20People%20Report%20to%20the%20Given%20Manager/README.md)

## Description

<p>Table: <code>Employees</code></p>

<pre>

+---------------+---------+

| Column Name   | Type    |

+---------------+---------+

| employee_id   | int     |

| employee_name | varchar |

| manager_id    | int     |

+---------------+---------+

employee_id is the primary key for this table.

Each row of this table indicates that the employee with ID employee_id and name employee_name reports his work to his/her direct manager with manager_id

The head of the company is the employee with employee_id = 1.

</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find&nbsp;<code>employee_id</code>&nbsp;of all employees that directly or indirectly report their work to the head of the company.</p>

<p>The indirect relation between managers will not exceed 3 managers as the company is small.</p>

<p>Return result table in any order without duplicates.</p>

<p>The query result format is in the following example:</p>

<pre>

<code>Employees </code>table:

+-------------+---------------+------------+

| employee_id | employee_name | manager_id |

+-------------+---------------+------------+

| 1           | Boss          | 1          |

| 3           | Alice         | 3          |

| 2           | Bob           | 1          |

| 4           | Daniel        | 2          |

| 7           | Luis          | 4          |

| 8           | Jhon          | 3          |

| 9           | Angela        | 8          |

| 77          | Robert        | 1          |

+-------------+---------------+------------+



<code>Result </code>table:

+-------------+

| employee_id |

+-------------+

| 2           |

| 77          |

| 4           |

| 7           |

+-------------+



The head of the company is the employee with employee_id 1.

The employees with employee_id 2 and 77 report their work directly to the head of the company.

The employee with employee_id 4 report his work indirectly to the head of the company 4 --&gt; 2 --&gt; 1. 

The employee with employee_id 7 report his work indirectly to the head of the company 7 --&gt; 4 --&gt; 2 --&gt; 1.

The employees with employee_id 3, 8 and 9 don&#39;t report their work to head of company directly or indirectly. 

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
