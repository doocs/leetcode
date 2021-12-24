# [577. Employee Bonus](https://leetcode.com/problems/employee-bonus)

[中文文档](/solution/0500-0599/0577.Employee%20Bonus/README.md)

## Description

<p>Select all employee&#39;s name and bonus whose bonus is &lt; 1000.</p>

<p>Table:<code>Employee </code></p>

<pre>

+-------+--------+-----------+--------+

| empId |  name  | supervisor| salary |

+-------+--------+-----------+--------+

|   1   | John   |  3        | 1000   |

|   2   | Dan    |  3        | 2000   |

|   3   | Brad   |  null     | 4000   |

|   4   | Thomas |  3        | 4000   |

+-------+--------+-----------+--------+

empId is the primary key column for this table.

</pre>

<p>Table: <code>Bonus</code></p>

<pre>

+-------+-------+

| empId | bonus |

+-------+-------+

| 2     | 500   |

| 4     | 2000  |

+-------+-------+

empId is the primary key column for this table.

</pre>

<p>Example ouput:</p>

<pre>

+-------+-------+

| name  | bonus |

+-------+-------+

| John  | null  |

| Dan   | 500   |

| Brad  | null  |

+-------+-------+

</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
