# [1225. 报告系统状态的连续日期](https://leetcode.cn/problems/report-contiguous-dates)

[English Version](/solution/1200-1299/1225.Report%20Contiguous%20Dates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Failed</code></p>

<pre>+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| fail_date    | date    |
+--------------+---------+
该表主键为 fail_date。
该表包含失败任务的天数.
</pre>

<p>Table: <code>Succeeded</code></p>

<pre>+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| success_date | date    |
+--------------+---------+
该表主键为 success_date。
该表包含成功任务的天数.
</pre>

<p>&nbsp;</p>

<p>系统 <strong>每天</strong> 运行一个任务。每个任务都独立于先前的任务。任务的状态可以是失败或是成功。</p>

<p>编写一个 SQL 查询&nbsp;<strong>2019-01-01</strong>&nbsp;到&nbsp;<strong>2019-12-31</strong> 期间任务连续同状态&nbsp;<code>period_state</code>&nbsp;的起止日期（<code>start_date</code> 和 <code>end_date</code>）。即如果任务失败了，就是失败状态的起止日期，如果任务成功了，就是成功状态的起止日期。</p>

<p>最后结果按照起始日期&nbsp;<code>start_date</code>&nbsp;排序</p>

<p>查询结果样例如下所示:</p>

<pre>Failed table:
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


Result table:
+--------------+--------------+--------------+
| period_state | start_date   | end_date     |
+--------------+--------------+--------------+
| succeeded    | 2019-01-01   | 2019-01-03   |
| failed       | 2019-01-04   | 2019-01-05   |
| succeeded    | 2019-01-06   | 2019-01-06   |
+--------------+--------------+--------------+

结果忽略了 2018 年的记录，因为我们只关心从 2019-01-01 到 2019-12-31 的记录
从 2019-01-01 到 2019-01-03 所有任务成功，系统状态为 &quot;succeeded&quot;。
从 2019-01-04 到 2019-01-05 所有任务失败，系统状态为 &quot;failed&quot;。
从 2019-01-06 到 2019-01-06 所有任务成功，系统状态为 &quot;succeeded&quot;。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
