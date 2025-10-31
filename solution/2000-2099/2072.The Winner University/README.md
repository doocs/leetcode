---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2072.The%20Winner%20University/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2072. 赢得比赛的大学 🔒](https://leetcode.cn/problems/the-winner-university)

[English Version](/solution/2000-2099/2072.The%20Winner%20University/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表： <code>NewYork</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
在 SQL 中，student_id 是这个表的主键。
每一行包含纽约大学 (New York University) 中一名学生一次考试的成绩。
</pre>

<p>&nbsp;</p>

<p>表： <code>California</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
在 SQL 中，student_id 是这个表的主键。
每一行包含加州大学 (California University) 中一名学生一次考试的成绩。
</pre>

<p>&nbsp;</p>

<p>纽约大学和加州大学之间举行了一场比赛。这场比赛由两所大学中相同数量的学生参加。拥有更多<strong>优秀学生</strong>的大学赢得这场比赛。如果两所大学的<strong>优秀学生</strong>数量相同，则这场比赛平局。</p>

<p><strong>优秀学生</strong>是指在考试中获得 <code>90</code>&nbsp;分或更高成绩的学生。</p>

<p>返回：</p>

<ul>
	<li><strong>"New York University"</strong> 若纽约大学赢得这场比赛。</li>
	<li><strong>"California University"</strong> 若加州大学赢得这场比赛。</li>
	<li><strong>"No Winner"</strong> 若这场比赛平局。</li>
</ul>

<p>返回结果格式如下示例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
NewYork 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 90    |
| 2          | 87    |
+------------+-------+
California 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 89    |
| 3          | 88    |
+------------+-------+
<strong>输出:</strong> 
+---------------------+
| winner              |
+---------------------+
| New York University |
+---------------------+
<strong>解释:</strong>
纽约大学有 1 名优秀学生，加州大学有 0 名优秀学生。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
NewYork 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 89    |
| 2          | 88    |
+------------+-------+
California 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 90    |
| 3          | 87    |
+------------+-------+
<strong>输出:</strong> 
+-----------------------+
| winner                |
+-----------------------+
| California University |
+-----------------------+
<strong>解释:</strong>
纽约大学有 0 名优秀学生，加州大学有 1 名优秀学生。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> 
NewYork 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 89    |
| 2          | 90    |
+------------+-------+
California 表:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 87    |
| 3          | 99    |
+------------+-------+
<strong>输出:</strong> 
+-----------+
| winner    |
+-----------+
| No Winner |
+-----------+
<strong>解释:</strong>
纽约大学和加州大学均有 1 名优秀学生。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN n1.cnt > n2.cnt THEN 'New York University'
        WHEN n1.cnt < n2.cnt THEN 'California University'
        ELSE 'No Winner'
    END AS winner
FROM
    (SELECT COUNT(1) AS cnt FROM NewYork WHERE score >= 90) AS n1,
    (SELECT COUNT(1) AS cnt FROM California WHERE score >= 90) AS n2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
