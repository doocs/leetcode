# [618. 学生地理信息报告](https://leetcode.cn/problems/students-report-by-geography)

[English Version](/solution/0600-0699/0618.Students%20Report%20By%20Geography/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>student</code>&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| name        | varchar |
| continent   | varchar |
+-------------+---------+
该表没有主键。它可能包含重复的行。
该表的每一行表示学生的名字和他们来自的大陆。
</pre>

<p>&nbsp;</p>

<p>一所学校有来自亚洲、欧洲和美洲的学生。</p>

<p>写一个查询语句实现对大洲（continent）列的&nbsp;<a href="https://zh.wikipedia.org/wiki/%E9%80%8F%E8%A7%86%E8%A1%A8" target="_blank">透视表</a> 操作，使得每个<code>学生</code>按照姓名的<strong>字母顺序</strong>依次排列在对应的大洲下面。输出的标题应依次为<code>美洲（America）、亚洲（Asia）和欧洲（Europe）。</code></p>

<p>测试用例的生成使得来自美国的学生人数不少于亚洲或欧洲的学生人数。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Student table:
+--------+-----------+
| name   | continent |
+--------+-----------+
| Jane   | America   |
| Pascal | Europe    |
| Xi     | Asia      |
| Jack   | America   |
+--------+-----------+
<strong>输出:</strong> 
+---------+------+--------+
| America | Asia | Europe |
+---------+------+--------+
| Jack    | Xi   | Pascal |
| Jane    | null | null   |
+---------+------+--------+</pre>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果不能确定哪个大洲的学生数最多，你可以写出一个查询去生成上述学生报告吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
