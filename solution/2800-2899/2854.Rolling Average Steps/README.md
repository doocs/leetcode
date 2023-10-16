# [2854. 滚动平均步数](https://leetcode.cn/problems/rolling-average-steps)

[English Version](/solution/2800-2899/2854.Rolling%20Average%20Steps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code><font face="monospace">Steps</font></code></p>

<pre>
+-------------+------+ 
| Column Name | Type | 
+-------------+------+ 
| user_id     | int  | 
| steps_count | int  |
| steps_date  | date |
+-------------+------+
(user_id, steps_date) 是此表的主键。
该表的每一行包含 user_id、steps_count 和 steps_date。
</pre>

<p>编写一个解决方案，计算出每个用户的&nbsp;<code>3-day</code> <strong>滚动平均步数&nbsp;</strong>。</p>

<p>计算 <code>n-day</code> <strong>滚动平均值 </strong>的计算方式如下：</p>

<ul>
	<li>对于每一天，如果有可用数据的情况下，我们会计算以该天为结束的 <code>n</code> 天连续步数的平均值，否则，对于该天来说，<code>n</code> 天滚动平均步数是未定义的。</li>
</ul>

<p>输出 <code>user_id</code>&nbsp;、 <code>steps_date</code><em> </em>和滚动平均值。并将滚动平均值四舍五入到&nbsp;<strong>两位小数</strong>。</p>

<p>返回结果表以<code>user_id</code><em>&nbsp;</em>和 <code>steps_date</code><em>&nbsp;</em><strong>升序</strong><em>&nbsp;</em>排序。</p>

<p>结果的格式如下示例。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<b>输入：</b>
Steps table:
+---------+-------------+------------+
| user_id | steps_count | steps_date |
+---------+-------------+------------+
| 1       | 687         | 2021-09-02 |
| 1       | 395         | 2021-09-04 |
| 1       | 499         | 2021-09-05 |
| 1       | 712         | 2021-09-06 |
| 1       | 576         | 2021-09-07 |
| 2       | 153         | 2021-09-06 |
| 2       | 171         | 2021-09-07 |
| 2       | 530         | 2021-09-08 |
| 3       | 945         | 2021-09-04 |
| 3       | 120         | 2021-09-07 |
| 3       | 557         | 2021-09-08 |
| 3       | 840         | 2021-09-09 |
| 3       | 627         | 2021-09-10 |
| 5       | 382         | 2021-09-05 |
| 6       | 480         | 2021-09-01 |
| 6       | 191         | 2021-09-02 |
| 6       | 303         | 2021-09-05 |
+---------+-------------+------------+
<b>输出：</b>
+---------+------------+-----------------+
| user_id | steps_date | rolling_average | 
+---------+------------+-----------------+
| 1       | 2021-09-06 | 535.33          | 
| 1       | 2021-09-07 | 595.67          | 
| 2       | 2021-09-08 | 284.67          |
| 3       | 2021-09-09 | 505.67          |
| 3       | 2021-09-10 | 674.67          |    
+---------+------------+-----------------+
<b>解释：</b>
- 对于 ID 为 1 的用户，截止到 2021-09-06 的三天连续的步数可用。因此，该日期的滚动平均值计算为 (395 + 499 + 712) / 3 = 535.33。
- 对于 ID 为 1 的用户，截止到 2021-09-07 的三天连续的步数可用。因此，该日期的滚动平均值计算为 (499 + 712 + 576) / 3 = 595.67。
- 对于 ID 为 2 的用户，截止到 2021-09-08 的三天连续的步数可用。因此，该日期的滚动平均值计算为 (153 + 171 + 530) / 3 = 284.67。
- 对于 ID 为 3 的用户，截止到 2021-09-09 的三天连续的步数可用。因此，该日期的滚动平均值计算为 (120 + 557 + 840) / 3 = 505.67。
- 对于 ID 为 3 的用户，截止到 2021-09-10 的三天连续的步数可用。因此，该日期的滚动平均值计算为 (557 + 840 + 627) / 3 = 674.67。
- 对于 ID 为 4 和 5 的用户，由于连续三天的数据不足，无法计算滚动平均值。结果表按 user_id 和 steps_date 升序排序。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：窗口函数**

我们用窗口函数 `LAG() OVER()` 来计算每个用户当前日期与上上个日期之间的天数差，如果为 $2$，说明这两个日期之间有连续 $3$ 天的数据，我们可以利用窗口函数 `AVG() OVER()` 来计算这 $3$ 个数据的平均值。

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            steps_date,
            ROUND(
                AVG(steps_count) OVER (
                    PARTITION BY user_id
                    ORDER BY steps_date
                    ROWS 2 PRECEDING
                ),
                2
            ) AS rolling_average,
            DATEDIFF(
                steps_date,
                LAG(steps_date, 2) OVER (
                    PARTITION BY user_id
                    ORDER BY steps_date
                )
            ) = 2 AS st
        FROM Steps
    )
SELECT
    user_id,
    steps_date,
    rolling_average
FROM T
WHERE st = 1
ORDER BY 1, 2;
```

<!-- tabs:end -->
