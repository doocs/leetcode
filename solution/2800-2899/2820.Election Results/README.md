# [2820. Election Results](https://leetcode.cn/problems/election-results)

[English Version](/solution/2800-2899/2820.Election%20Results/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code><font face="monospace">Votes</font></code></p>

<pre>
+-------------+---------+ 
| Column Name | Type    | 
+-------------+---------+ 
| voter       | varchar | 
| candidate   | varchar |
+-------------+---------+
(voter, candidate) is the primary key for this table.
Each row of this table contains name of the voter and their candidate. 
</pre>

<p>The election is conducted in a city where everyone can vote for <strong>one or more</strong> candidates or choose <strong>not</strong> to vote. Each person has <code>1</code><strong> vote</strong> so if they vote for multiple candidates, their vote gets equally split across them. For example, if a person votes for <code>2</code> candidates, these candidates receive an equivalent of <code>0.5</code>&nbsp;votes each.</p>

<p>Write an SQL query to find <code>candidate</code> who got the most votes and won the election. Output the name of the <strong>candidate</strong> or If multiple candidates have an <strong>equal number</strong> of votes, display the names of all of them.</p>

<p>Return<em> the result table ordered</em> <em>by</em> <code>candidate</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
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
<strong>Output:</strong> 
+-----------+
| candidate | 
+-----------+
| Christine |  
| Ryan      |  
+-----------+
<strong>Explanation:</strong> 
- Kathy and Terry opted not to participate in voting, resulting in their votes being recorded as 0. Charles distributed his vote among three candidates, equating to 0.33 for each candidate. On the other hand, Benjamin, Arthur, Anthony, Edward, and Evelyn each cast their votes for a single candidate.
- Collectively, Candidate Ryan and Christine amassed a total of 2.33 votes, while Kathy received a combined total of 1.33 votes.
Since Ryan and Christine received an equal number of votes, we will display their names in ascending order.</pre>

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
