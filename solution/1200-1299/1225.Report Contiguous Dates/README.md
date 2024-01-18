# [1225. 报告系统状态的连续日期](https://leetcode.cn/problems/report-contiguous-dates)

[English Version](/solution/1200-1299/1225.Report%20Contiguous%20Dates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Failed</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| fail_date    | date    |
+--------------+---------+
该表主键为 fail_date (具有唯一值的列)。
该表包含失败任务的天数.
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Succeeded</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| success_date | date    |
+--------------+---------+
该表主键为 success_date (具有唯一值的列)。
该表包含成功任务的天数.
</pre>

<p>&nbsp;</p>

<p>系统 <strong>每天</strong> 运行一个任务。每个任务都独立于先前的任务。任务的状态可以是失败或是成功。</p>

<p>编写解决方案找出&nbsp;<strong>2019-01-01</strong>&nbsp;到&nbsp;<strong>2019-12-31</strong> 期间任务连续同状态&nbsp;<code>period_state</code>&nbsp;的起止日期（<code>start_date</code> 和 <code>end_date</code>）。即如果任务失败了，就是失败状态的起止日期，如果任务成功了，就是成功状态的起止日期。</p>

<p>最后结果按照起始日期&nbsp;<code>start_date</code>&nbsp;排序</p>

<p>返回结果样例如下所示:</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
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
<strong>输出：</strong>
+--------------+--------------+--------------+
| period_state | start_date   | end_date     |
+--------------+--------------+--------------+
| succeeded    | 2019-01-01   | 2019-01-03   |
| failed       | 2019-01-04   | 2019-01-05   |
| succeeded    | 2019-01-06   | 2019-01-06   |
+--------------+--------------+--------------+
<strong>解释：</strong>
结果忽略了 2018 年的记录，因为我们只关心从 2019-01-01 到 2019-12-31 的记录
从 2019-01-01 到 2019-01-03 所有任务成功，系统状态为 "succeeded"。
从 2019-01-04 到 2019-01-05 所有任务失败，系统状态为 "failed"。
从 2019-01-06 到 2019-01-06 所有任务成功，系统状态为 "succeeded"。
</pre>

## 解法

### 方法一：合并 + 窗口函数 + 分组求最大最小值

我们可以将两个表合并，用一个字段 $st$ 表示状态，其中 `failed` 表示失败，`succeeded` 表示成功。然后我们可以使用窗口函数，将相同状态的记录分到一组，求出每个日期与其所在组排名的差值 $pt$，作为同一个连续状态的标识。最后我们可以按照 $st$ 和 $pt$ 分组，求出每组的最小日期和最大日期，然后按照最小日期排序即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT fail_date AS dt, 'failed' AS st
        FROM Failed
        WHERE YEAR(fail_date) = 2019
        UNION ALL
        SELECT success_date AS dt, 'succeeded' AS st
        FROM Succeeded
        WHERE YEAR(success_date) = 2019
    )
SELECT
    st AS period_state,
    MIN(dt) AS start_date,
    MAX(dt) AS end_date
FROM
    (
        SELECT
            *,
            SUBDATE(
                dt,
                RANK() OVER (
                    PARTITION BY st
                    ORDER BY dt
                )
            ) AS pt
        FROM T
    ) AS t
GROUP BY 1, pt
ORDER BY 2;
```

<!-- tabs:end -->

<!-- end -->
