# [1225. Report Contiguous Dates](https://leetcode.com/problems/report-contiguous-dates)

[中文文档](/solution/1200-1299/1225.Report%20Contiguous%20Dates/README.md)

## Description

<p>Table: <code>Failed</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| fail_date    | date    |
+--------------+---------+
fail_date is the primary key for this table.
This table contains the days of failed tasks.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Succeeded</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| success_date | date    |
+--------------+---------+
success_date is the primary key for this table.
This table contains the days of succeeded tasks.
</pre>

<p>&nbsp;</p>

<p>A system is running one task <strong>every day</strong>. Every task is independent of the previous tasks. The tasks can fail or succeed.</p>

<p>Write an SQL query to generate a report of <code>period_state</code> for each continuous interval of days in the period from <code>2019-01-01</code> to <code>2019-12-31</code>.</p>

<p><code>period_state</code> is <em>&#39;</em><code>failed&#39;</code><em> </em>if tasks in this interval failed or <code>&#39;succeeded&#39;</code> if tasks in this interval succeeded. Interval of days are retrieved as <code>start_date</code> and <code>end_date.</code></p>

<p>Return the result table ordered by <code>start_date</code>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Failed table:
+-------------------+
| fail_date         |
+-------------------+
| 2018-12-28        |
| 2018-12-29        |
| 2019-01-04        |
| 2019-01-05        |
+-------------------+
Succeeded table:
+-------------------+
| success_date      |
+-------------------+
| 2018-12-30        |
| 2018-12-31        |
| 2019-01-01        |
| 2019-01-02        |
| 2019-01-03        |
| 2019-01-06        |
+-------------------+
<strong>Output:</strong> 
+--------------+--------------+--------------+
| period_state | start_date   | end_date     |
+--------------+--------------+--------------+
| succeeded    | 2019-01-01   | 2019-01-03   |
| failed       | 2019-01-04   | 2019-01-05   |
| succeeded    | 2019-01-06   | 2019-01-06   |
+--------------+--------------+--------------+
<strong>Explanation:</strong> 
The report ignored the system state in 2018 as we care about the system in the period 2019-01-01 to 2019-12-31.
From 2019-01-01 to 2019-01-03 all tasks succeeded and the system state was &quot;succeeded&quot;.
From 2019-01-04 to 2019-01-05 all tasks failed and the system state was &quot;failed&quot;.
From 2019-01-06 to 2019-01-06 all tasks succeeded and the system state was &quot;succeeded&quot;.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
