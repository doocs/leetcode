# [1179. Reformat Department Table](https://leetcode.com/problems/reformat-department-table)

[中文文档](/solution/1100-1199/1179.Reformat%20Department%20Table/README.md)

## Description

<p>Table: <code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| revenue     | int     |
| month       | varchar |
+-------------+---------+
(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in [&quot;Jan&quot;,&quot;Feb&quot;,&quot;Mar&quot;,&quot;Apr&quot;,&quot;May&quot;,&quot;Jun&quot;,&quot;Jul&quot;,&quot;Aug&quot;,&quot;Sep&quot;,&quot;Oct&quot;,&quot;Nov&quot;,&quot;Dec&quot;].
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to reformat the table such that there is a department id column and a revenue column <strong>for each month</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+
<strong>Output:</strong> 
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+
<strong>Explanation:</strong> The revenue from Apr to Dec is null.
Note that the result table has 13 columns (1 for the department id + 12 for the months).
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
select
    id,
    sum(
        case
            month
            when 'Jan' then revenue
        end
    ) as Jan_Revenue,
    sum(
        case
            month
            when 'Feb' then revenue
        end
    ) as Feb_Revenue,
    sum(
        case
            month
            when 'Mar' then revenue
        end
    ) as Mar_Revenue,
    sum(
        case
            month
            when 'Apr' then revenue
        end
    ) as Apr_Revenue,
    sum(
        case
            month
            when 'May' then revenue
        end
    ) as May_Revenue,
    sum(
        case
            month
            when 'Jun' then revenue
        end
    ) as Jun_Revenue,
    sum(
        case
            month
            when 'Jul' then revenue
        end
    ) as Jul_Revenue,
    sum(
        case
            month
            when 'Aug' then revenue
        end
    ) as Aug_Revenue,
    sum(
        case
            month
            when 'Sep' then revenue
        end
    ) as Sep_Revenue,
    sum(
        case
            month
            when 'Oct' then revenue
        end
    ) as Oct_Revenue,
    sum(
        case
            month
            when 'Nov' then revenue
        end
    ) as Nov_Revenue,
    sum(
        case
            month
            when 'Dec' then revenue
        end
    ) as Dec_Revenue
from
    Department
group by
    id
```

<!-- tabs:end -->
