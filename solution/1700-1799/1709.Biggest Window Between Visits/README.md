# [1709. 访问日期之间最大的空档期 🔒](https://leetcode.cn/problems/biggest-window-between-visits)

[English Version](/solution/1700-1799/1709.Biggest%20Window%20Between%20Visits/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>UserVisits</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| user_id     | int  |
| visit_date  | date |
+-------------+------+
该表没有主键，它可能有重复的行
该表包含用户访问某特定零售商的日期日志。</pre>

<p>&nbsp;</p>

<p>假设今天的日期是&nbsp;<code>'2021-1-1'</code>&nbsp;。</p>

<p>编写解决方案，对于每个&nbsp;<code>user_id</code>&nbsp;，求出每次访问及其下一个访问（若该次访问是最后一次，则为今天）之间最大的空档期天数&nbsp;<code>window</code>&nbsp;。</p>

<p>返回结果表，按用户编号&nbsp;<code>user_id</code>&nbsp;排序。</p>

<p>结果格式如下示例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
UserVisits 表：
+---------+------------+
| user_id | visit_date |
+---------+------------+
| 1       | 2020-11-28 |
| 1       | 2020-10-20 |
| 1       | 2020-12-3  |
| 2       | 2020-10-5  |
| 2       | 2020-12-9  |
| 3       | 2020-11-11 |
+---------+------------+
<strong>输出：</strong>
+---------+---------------+
| user_id | biggest_window|
+---------+---------------+
| 1       | 39            |
| 2       | 65            |
| 3       | 51            |
+---------+---------------+
<strong>解释：</strong>
对于第一个用户，问题中的空档期在以下日期之间：
    - 2020-10-20 至 2020-11-28 ，共计 39 天。
    - 2020-11-28 至 2020-12-3 ，共计 5 天。
    - 2020-12-3 至 2021-1-1 ，共计 29 天。
由此得出，最大的空档期为 39 天。
对于第二个用户，问题中的空档期在以下日期之间：
    - 2020-10-5 至 2020-12-9 ，共计 65 天。
    - 2020-12-9 至 2021-1-1 ，共计 23 天。
由此得出，最大的空档期为 65 天。
对于第三个用户，问题中的唯一空档期在 2020-11-11 至 2021-1-1 之间，共计 51 天。</pre>

## 解法

### 方法一：窗口函数

我们可以使用窗口函数 `LEAD` 来获取每个用户每次访问的下一次访问的日期（如果下一次访问的日期不存在，则视为 `2021-1-1`），然后利用 `DATEDIFF` 函数来计算两次访问之间的天数差值，最后对每个用户的天数差值求最大值即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            user_id,
            DATEDIFF(
                LEAD(visit_date, 1, '2021-1-1') OVER (
                    PARTITION BY user_id
                    ORDER BY visit_date
                ),
                visit_date
            ) AS diff
        FROM UserVisits
    )
SELECT user_id, MAX(diff) AS biggest_window
FROM T
GROUP BY 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
