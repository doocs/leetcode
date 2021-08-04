# [574. 当选者](https://leetcode-cn.com/problems/winning-candidate)

[English Version](/solution/0500-0599/0574.Winning%20Candidate/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Candidate</code></p>

<pre>+-----+---------+
| id  | Name    |
+-----+---------+
| 1   | A       |
| 2   | B       |
| 3   | C       |
| 4   | D       |
| 5   | E       |
+-----+---------+  
</pre>

<p>表: <code>Vote</code></p>

<pre>+-----+--------------+
| id  | CandidateId  |
+-----+--------------+
| 1   |     2        |
| 2   |     4        |
| 3   |     3        |
| 4   |     2        |
| 5   |     5        |
+-----+--------------+
id 是自动递增的主键，
CandidateId 是 Candidate 表中的 id.
</pre>

<p>请编写 sql 语句来找到当选者的名字，上面的例子将返回当选者 <code>B</code>.</p>

<pre>+------+
| Name |
+------+
| B    |
+------+
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>你可以假设<strong>没有平局</strong>，换言之，<strong>最多</strong>只有一位当选者。</li>
</ol>

<p>&nbsp;</p>


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
        FROM
            Vote
        GROUP BY
            CandidateId
        ORDER BY
            COUNT(id) DESC
        LIMIT 1
    ) AS t
INNER JOIN
    Candidate c
ON
    t.id = c.id;
```

<!-- tabs:end -->

