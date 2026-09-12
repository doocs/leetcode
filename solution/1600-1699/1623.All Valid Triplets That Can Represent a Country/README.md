---
comments: true
difficulty: 简单
tags:
    - 数据库
---

<!-- problem:start -->

# [1623. 三人国家代表队 🔒](https://leetcode.cn/problems/all-valid-triplets-that-can-represent-a-country)

[English Version](/solution/1600-1699/1623.All%20Valid%20Triplets%20That%20Can%20Represent%20a%20Country/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>SchoolA</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id 是该表具有唯一值的列
表中的每一行包含了学校 A 中每一个学生的名字和 ID
所有 student_name 在表中都是独一无二的
</pre>

<p>&nbsp;</p>

<p>表: <code>SchoolB</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id 是该表具有唯一值的列
表中的每一行包含了学校 B 中每一个学生的名字和 ID
所有 student_name 在表中都是独一无二的
</pre>

<p>&nbsp;</p>

<p>表: <code>SchoolC</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id 是该表具有唯一值的列
表中的每一行包含了学校 C 中每一个学生的名字和 ID
所有 student_name 在表中都是独一无二的
</pre>

<p>&nbsp;</p>

<p>有一个国家只有三所学校，这个国家的每一个学生只会注册&nbsp;<strong>一所学校</strong>。</p>

<p>这个国家正在参加一个竞赛，他们希望从这三所学校中各选出一个学生来组建一支三人的代表队。例如：</p>

<ul>
	<li><code>member_A</code>&nbsp;是从 <code>SchoolA</code>&nbsp;中选出的</li>
	<li><code>member_B</code>&nbsp;是从 <code>SchoolB</code>&nbsp;中选出的</li>
	<li><code>member_C</code>&nbsp;是从 <code>SchoolC</code>&nbsp;中选出的</li>
	<li>被选中的学生具有不同的名字和 ID（没有任何两个学生拥有相同的名字、没有任何两个学生拥有相同的 ID）</li>
</ul>

<p>使用上述条件，编写一个解决方案来找到所有可能的三人国家代表队组合。</p>

<p>返回结果 <strong>无顺序要求</strong>。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
<code>SchoolA</code> table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 1          | Alice        |
| 2          | Bob          |
+------------+--------------+
<code>SchoolB</code> table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 3          | Tom          |
+------------+--------------+
<code>SchoolC</code> table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 3          | Tom          |
| 2          | Jerry        |
| 10         | Alice        |
+------------+--------------+
<strong>输出：</strong>
+----------+----------+----------+
| member_A | member_B | member_C |
+----------+----------+----------+
| Alice    | Tom      | Jerry    |
| Bob      | Tom      | Alice    |
+----------+----------+----------+
<strong>解释：</strong>
让我们看看有哪些可能的组合：
- (Alice, Tom, Tom) --&gt; 不适用，因为member_B（Tom）和member_C（Tom）有相同的名字和ID
- (Alice, Tom, Jerry) --&gt; 可能的组合
- (Alice, Tom, Alice) --&gt; 不适用，因为member_A和member_C有相同的名字
- (Bob, Tom, Tom) --&gt; 不适用，因为member_B和member_C有相同的名字和ID
- (Bob, Tom, Jerry) --&gt; 不适用，因为member_A和member_C有相同的ID
- (Bob, Tom, Alice) --&gt; 可能的组合.</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 要从三所学校各选一名学生，且三人学号互不相同、姓名互不相同。表规模很小，三重笛卡尔积再过滤即可。
>
> 在 $\texttt{WHERE}$ 中同时限制三对 $\texttt{student\_id}$ 与三对 $\texttt{student\_name}$ 均不相等，选出的 $(\texttt{member\_A},\texttt{member\_B},\texttt{member\_C})$ 即为全部合法三人组。

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    a.student_name AS member_A,
    b.student_name AS member_B,
    c.student_name AS member_C
FROM
    SchoolA AS a,
    SchoolB AS b,
    SchoolC AS c
WHERE
    a.student_name != b.student_name
    AND a.student_name != c.student_name
    AND b.student_name != c.student_name
    AND a.student_id != b.student_id
    AND a.student_id != c.student_id
    AND b.student_id != c.student_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
