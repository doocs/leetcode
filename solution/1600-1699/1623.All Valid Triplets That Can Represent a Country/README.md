# [1623. 三人国家代表队](https://leetcode.cn/problems/all-valid-triplets-that-can-represent-a-country)

[English Version](/solution/1600-1699/1623.All%20Valid%20Triplets%20That%20Can%20Represent%20a%20Country/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>SchoolA</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id 是表的主键
表中的每一行包含了学校A中每一个学生的名字和ID
所有student_name在表中都是独一无二的
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
student_id 是表的主键
表中的每一行包含了学校B中每一个学生的名字和ID
所有student_name在表中都是独一无二的
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
student_id 是表的主键
表中的每一行包含了学校C中每一个学生的名字和ID
所有student_name在表中都是独一无二的
</pre>

<p>&nbsp;</p>

<p>有一个国家只有三所学校，这个国家的每一个学生只会注册<strong>一所学校</strong>。</p>

<p>这个国家正在参加一个竞赛，他们希望从这三所学校中各选出一个学生来组建一支三人的代表队。</p>

<p>例如：</p>

<ul>
	<li><code>member_A</code>是从 <code>SchoolA</code>中选出的</li>
	<li><code>member_B</code>是从 <code>SchoolB</code>中选出的</li>
	<li><code>member_C</code>是从 <code>SchoolC</code>中选出的</li>
	<li>被选中的学生具有不同的名字和ID（没有任何两个学生拥有相同的名字、没有任何两个学生拥有相同的ID）</li>
</ul>

<p>使用上述条件，编写SQL查询语句来找到所有可能的三人国家代表队组合。</p>

<p>查询结果接受<strong>任何顺序。</strong></p>

<p>查询结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<code><strong>输入：</strong>
SchoolA</code> table:
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
