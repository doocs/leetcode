# [2820. 选举结果](https://leetcode.cn/problems/election-results)

[English Version](/solution/2800-2899/2820.Election%20Results/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code><font face="monospace">Votes</font></code></p>

<pre>
+-------------+---------+ 
| Column Name | Type    | 
+-------------+---------+ 
| voter       | varchar | 
| candidate   | varchar |
+-------------+---------+
(voter, candidate) 是该表的主键。
该表的每一行都包含选民及其候选人的姓名。
</pre>

<p>选举在一个城市进行，每个人都可以投票给 <strong>一个或多个</strong> 候选人，也可以选择 <strong>不</strong> 投票。每个人都有 <code>1</code> 票，所以如果他们投票给多个候选人，他们的选票会被平均分配。例如，如果一个人投票给 <code>2</code> 个候选人，这些候选人每人获得&nbsp;<code>0.5</code> 张选票。</p>

<p>编写一个 SQL 查询来查找获得最多选票并赢得选举的候选人&nbsp;<code>candidate</code> 。输出 <strong>候选人</strong> 的姓名，或者如果多个候选人的票数 <strong>相等</strong> ，则输出所有候选人的姓名。</p>

<p>返回按 <code>candidate</code>&nbsp;<strong>升序排序&nbsp;</strong>的结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Votes table:
+----------+-----------+
| voter    | candidate |
+----------+-----------+
| Kathy    | null      |
| Charles  | Ryan      |
| Charles  | Christine |
| Charles  | Kathy     |
| Benjamin | Christine |
| Anthony  | Ryan      |
| Edward   | Ryan      |
| Terry    | null      |
| Evelyn   | Kathy     |
| Arthur   | Christine |
+----------+-----------+
<b>输出：</b>
+-----------+
| candidate | 
+-----------+
| Christine |  
| Ryan      |  
+-----------+
<b>解释：</b>
- Kathy 和 Terry 选择不投票，导致他们的投票被记录为 0。 Charles 将他的选票分配给了三位候选人，相当于每位候选人得到 0.33 票。另一方面，Benjamin, Arthur, Anthony, Edward, 和 Evely 各自把票投给了一位候选人。
- Ryan 和 Christine 总共获得了2.33票，而 Kathy 总共获得了 1.33 票。
由于 Ryan 和 Christine 获得的票数相等，我们将按升序显示他们的名字。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：窗口函数 + 分组统计**

我们可以使用窗口函数 `count` 计算每个投票人投给的候选人的票数，然后再使用分组统计函数 `sum` 计算每个候选人的总票数，最后使用窗口函数 `rank` 计算每个候选人的排名，最后筛选出排名第一的候选人即可。

注意，结果集中可能会有多个排名第一的候选人，因此我们需要使用 `order by` 对候选人进行排序。

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT candidate, sum(vote) AS tot
        FROM
            (
                SELECT
                    candidate,
                    1 / (count(candidate) OVER (PARTITION BY voter)) AS vote
                FROM Votes
                WHERE candidate IS NOT NULL
            ) AS t
        GROUP BY 1
    ),
    P AS (
        SELECT
            candidate,
            rank() OVER (ORDER BY tot DESC) AS rk
        FROM T
    )
SELECT candidate
FROM P
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->
