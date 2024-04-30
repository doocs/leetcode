# [1336. 每次访问的交易次数 🔒](https://leetcode.cn/problems/number-of-transactions-per-visit)

[English Version](/solution/1300-1399/1336.Number%20of%20Transactions%20per%20Visit/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Visits</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| visit_date    | date    |
+---------------+---------+
(user_id, visit_date) 是该表的主键(具有唯一值的列的组合)
该表的每行表示 user_id 在 visit_date 访问了银行
</pre>

<p>&nbsp;</p>

<p>表: <code>Transactions</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| user_id          | int     |
| transaction_date | date    |
| amount           | int     |
+------------------+---------+
该表可能有重复行
该表的每一行表示 user_id 在 transaction_date 完成了一笔 amount 数额的交易
可以保证用户 (user) 在 transaction_date 访问了银行 (也就是说 Visits 表包含 (user_id, transaction_date) 行)
</pre>

<p>&nbsp;</p>

<p>银行想要得到银行客户在一次访问时的交易次数和相应的在一次访问时该交易次数的客户数量的图表</p>

<p>编写解决方案找出多少客户访问了银行但没有进行任何交易，多少客户访问了银行进行了一次交易等等</p>

<p>结果包含两列：</p>

<ul>
	<li><code>transactions_count：</code>&nbsp;客户在一次访问中的交易次数</li>
	<li><code>visits_count：</code> 在&nbsp;<code>transactions_count</code>&nbsp;交易次数下相应的一次访问时的客户数量</li>
</ul>

<p><code>transactions_count</code> 的值从&nbsp;<code>0</code>&nbsp;到所有用户一次访问中的&nbsp;<code>max(transactions_count)</code>&nbsp;</p>

<p>结果按&nbsp;<code>transactions_count</code>&nbsp;排序</p>

<p>下面是返回结果格式的例子：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1336.Number%20of%20Transactions%20per%20Visit/images/chart.png" style="height:359px; width:600px" /></p>

<pre>
<code><strong>输入：</strong>
Visits</code> 表:
+---------+------------+
| user_id | visit_date |
+---------+------------+
| 1       | 2020-01-01 |
| 2       | 2020-01-02 |
| 12      | 2020-01-01 |
| 19      | 2020-01-03 |
| 1       | 2020-01-02 |
| 2       | 2020-01-03 |
| 1       | 2020-01-04 |
| 7       | 2020-01-11 |
| 9       | 2020-01-25 |
| 8       | 2020-01-28 |
+---------+------------+
<code>Transactions</code> 表:
+---------+------------------+--------+
| user_id | transaction_date | amount |
+---------+------------------+--------+
| 1       | 2020-01-02       | 120    |
| 2       | 2020-01-03       | 22     |
| 7       | 2020-01-11       | 232    |
| 1       | 2020-01-04       | 7      |
| 9       | 2020-01-25       | 33     |
| 9       | 2020-01-25       | 66     |
| 8       | 2020-01-28       | 1      |
| 9       | 2020-01-25       | 99     |
+---------+------------------+--------+
<strong>输出：</strong>
+--------------------+--------------+
| <code>transactions_count</code> | visits_count |
+--------------------+--------------+
| 0                  | 4            |
| 1                  | 5            |
| 2                  | 0            |
| 3                  | 1            |
+--------------------+--------------+
<strong>解释：</strong>为这个例子绘制的图表如上所示
* 对于 transactions_count = 0, visits 中 (1, "2020-01-01"), (2, "2020-01-02"), (12, "2020-01-01") 和 (19, "2020-01-03") 没有进行交易，所以 visits_count = 4 。
* 对于 transactions_count = 1, visits 中 (2, "2020-01-03"), (7, "2020-01-11"), (8, "2020-01-28"),&nbsp;(1, "2020-01-02") 和 (1, "2020-01-04") 进行了一次交易，所以 visits_count = 5 。
* 对于 transactions_count = 2, 没有客户访问银行进行了两次交易，所以 visits_count = 0 。
* 对于 transactions_count = 3, visits 中&nbsp;(9, "2020-01-25") 进行了三次交易，所以 visits_count = 1 。
* 对于 transactions_count &gt;= 4, 没有客户访问银行进行了超过3次交易，所以我们停止在 transactions_count = 3 。
</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH RECURSIVE
    S AS (
        SELECT 0 AS n
        UNION
        SELECT n + 1
        FROM S
        WHERE
            n < (
                SELECT MAX(cnt)
                FROM
                    (
                        SELECT COUNT(1) AS cnt
                        FROM Transactions
                        GROUP BY user_id, transaction_date
                    ) AS t
            )
    ),
    T AS (
        SELECT v.user_id, visit_date, IFNULL(cnt, 0) AS cnt
        FROM
            Visits AS v
            LEFT JOIN (
                SELECT user_id, transaction_date, COUNT(1) AS cnt
                FROM Transactions
                GROUP BY 1, 2
            ) AS t
                ON v.user_id = t.user_id AND v.visit_date = t.transaction_date
    )
SELECT n AS transactions_count, COUNT(user_id) AS visits_count
FROM
    S AS s
    LEFT JOIN T AS t ON s.n = t.cnt
GROUP BY n
ORDER BY n;
```

<!-- tabs:end -->

<!-- end -->
