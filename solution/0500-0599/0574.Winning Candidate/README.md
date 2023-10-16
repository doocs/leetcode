# [574. 当选者](https://leetcode.cn/problems/winning-candidate)

[English Version](/solution/0500-0599/0574.Winning%20Candidate/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Candidate</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| name        | varchar  |
+-------------+----------+
id 是该表中具有唯一值的列
该表的每一行都包含关于候选对象的id和名称的信息。</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Vote</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| candidateId | int  |
+-------------+------+
id 是自动递增的主键(具有唯一值的列)。
candidateId是id来自Candidate表的外键(reference 列)。
该表的每一行决定了在选举中获得第i张选票的候选人。</pre>

<p>&nbsp;</p>

<p>编写解决方案来报告获胜候选人的名字(即获得最多选票的候选人)。</p>

<p>生成的测试用例保证&nbsp;<strong>只有一个候选人赢得&nbsp;</strong>选举。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Candidate table:
+----+------+
| id | name |
+----+------+
| 1  | A    |
| 2  | B    |
| 3  | C    |
| 4  | D    |
| 5  | E    |
+----+------+
Vote table:
+----+-------------+
| id | candidateId |
+----+-------------+
| 1  | 2           |
| 2  | 4           |
| 3  | 3           |
| 4  | 2           |
| 5  | 5           |
+----+-------------+
<strong>输出:</strong> 
+------+
| name |
+------+
| B    |
+------+
<strong>解释:</strong> 
候选人B有2票。候选人C、D、E各有1票。
获胜者是候选人B。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    Name
FROM
    (
        SELECT
            CandidateId AS id
        FROM Vote
        GROUP BY CandidateId
        ORDER BY COUNT(id) DESC
        LIMIT 1
    ) AS t
    INNER JOIN Candidate AS c ON t.id = c.id;
```

```sql
# Write your MySQL query statement below
SELECT name
FROM
    Candidate AS c
    LEFT JOIN Vote AS v ON c.id = v.candidateId
GROUP BY c.id
ORDER BY COUNT(1) DESC
LIMIT 1;
```

<!-- tabs:end -->
