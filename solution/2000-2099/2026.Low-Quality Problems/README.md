# [2026. 低质量的问题](https://leetcode.cn/problems/low-quality-problems)

[English Version](/solution/2000-2099/2026.Low-Quality%20Problems/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Problems</code></p>

<pre>
+-------------+------+
| 列名         | 类型 |
+-------------+------+
| problem_id  | int  |
| likes       | int  |
| dislikes    | int  |
+-------------+------+
在 SQL 中，problem_id 是这张表的主键。
该表的每一行都表示一个力扣问题的喜欢和不喜欢的数量。
</pre>

<p>&nbsp;</p>

<p>找出&nbsp;<strong>低质量&nbsp;</strong>问题的 ID 集合。如果一个力扣问题的喜欢率（喜欢数除以总投票数）<strong>严格低于&nbsp;</strong><code>60%</code><strong>&nbsp;</strong>，则该问题为<strong>低质量</strong>问题。</p>

<p>按&nbsp;<code>problem_id</code> 升序排列返回结果表。</p>

<p>结果表的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
Problems 表:
+------------+-------+----------+
| problem_id | likes | dislikes |
+------------+-------+----------+
| 6          | 1290  | 425      |
| 11         | 2677  | 8659     |
| 1          | 4446  | 2760     |
| 7          | 8569  | 6086     |
| 13         | 2050  | 4164     |
| 10         | 9002  | 7446     |
+------------+-------+----------+
<strong>输出:</strong> 
+------------+
| problem_id |
+------------+
| 7          |
| 10         |
| 11         |
| 13         |
+------------+
<strong>解释:</strong> 喜欢的比率如下:
- 问题 1: (4446 / (4446 + 2760)) * 100 = 61.69858%
- 问题 6: (1290 / (1290 + 425)) * 100 = 75.21866%
- 问题 7: (8569 / (8569 + 6086)) * 100 = 58.47151%
- 问题 10: (9002 / (9002 + 7446)) * 100 = 54.73006%
- 问题 11: (2677 / (2677 + 8659)) * 100 = 23.61503%
- 问题 13: (2050 / (2050 + 4164)) * 100 = 32.99002%
问题 7, 10, 11, 和 13 是低质量问题，因为它们的同类百分比低于60%。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT problem_id
FROM Problems
WHERE likes / (likes + dislikes) < 0.6
ORDER BY problem_id;
```

<!-- tabs:end -->

<!-- end -->
