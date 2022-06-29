# [596. 超过 5 名学生的课](https://leetcode.cn/problems/classes-more-than-5-students)

[English Version](/solution/0500-0599/0596.Classes%20More%20Than%205%20Students/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Courses</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student     | varchar |
| class       | varchar |
+-------------+---------+
(student, class)是该表的主键列。
该表的每一行表示学生的名字和他们注册的班级。
</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询来报告 <strong>至少有5个学生</strong> 的所有班级。</p>

<p>以 <strong>任意顺序 </strong>返回结果表。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Courses table:
+---------+----------+
| student | class    |
+---------+----------+
| A       | Math     |
| B       | English  |
| C       | Math     |
| D       | Biology  |
| E       | Math     |
| F       | Computer |
| G       | Math     |
| H       | Math     |
| I       | Math     |
+---------+----------+
<strong>输出:</strong> 
+---------+ 
| class &nbsp; | 
+---------+ 
| Math &nbsp; &nbsp;| 
+---------+
<strong>解释: </strong>
-数学课有6个学生，所以我们包括它。
-英语课有1名学生，所以我们不包括它。
-生物课有1名学生，所以我们不包括它。
-计算机课有1个学生，所以我们不包括它。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT
	class
FROM
	courses
GROUP BY
	class
HAVING
	COUNT( class ) >= 5
```

<!-- tabs:end -->
