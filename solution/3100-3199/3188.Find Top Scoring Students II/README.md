---
comments: true
difficulty: 困难
tags:
    - 数据库
---

<!-- problem:start -->

# [3188. 查找得分最高的学生 II 🔒](https://leetcode.cn/problems/find-top-scoring-students-ii)

[English Version](/solution/3100-3199/3188.Find%20Top%20Scoring%20Students%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>students</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| name        | varchar  |
| major       | varchar  |
+-------------+----------+
student_id 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含学生 ID，学生姓名和他们的专业。
</pre>

<p>表：<code>courses</code></p>

<pre>
+-------------+-------------------+
| Column Name | Type              |       
+-------------+-------------------+
| course_id   | int               |    
| name        | varchar           |      
| credits     | int               |           
| major       | varchar           |       
| mandatory   | enum              |      
+-------------+-------------------+
course_id 是这张表的主键。 
mandatory 是 ('Yes', 'No') 的枚举类型。
每一行包含课程 ID，课程名，学分，所属专业，以及该课程是否必修。
</pre>

<p>表：<code>enrollments</code></p>

<pre>
+-------------+----------+
| Column Name | Type     | 
+-------------+----------+
| student_id  | int      |
| course_id   | int      |
| semester    | varchar  |
| grade       | varchar  |
| GPA         | decimal  | 
+-------------+----------+
(student_id, course_id, semester) 是这张表的主键（有不同值的列的组合）。
这张表的每一行包含学生 ID，课程 ID，学期和获得的学分。
</pre>

<p>编写一个解决方案来查找满足下述标准的学生：</p>

<ul>
	<li>已经 <strong>修完他们专业中所有的必修课程</strong> 和 <strong>至少两个&nbsp;</strong>选修课程。</li>
	<li>在 <strong>所有必修课程</strong> 中取得等级 <strong>A</strong> 并且 <strong>选修课程</strong> 至少取得 <strong>B</strong>。</li>
	<li>保持他们所有课程（包括不属于他们专业的）的平均&nbsp;<code>GPA</code>&nbsp;至少在&nbsp;<code>2.5</code>&nbsp;以上。</li>
</ul>

<p>返回结果表以&nbsp;<code>student_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>students 表：</p>

<pre class="example-io">
 +------------+------------------+------------------+
 | student_id | name             | major            |
 +------------+------------------+------------------+
 | 1          | Alice            | Computer Science |
 | 2          | Bob              | Computer Science |
 | 3          | Charlie          | Mathematics      |
 | 4          | David            | Mathematics      |
 +------------+------------------+------------------+
 </pre>

<p>courses 表：</p>

<pre class="example-io">
 +-----------+-------------------+---------+------------------+----------+
 | course_id | name              | credits | major            | mandatory|
 +-----------+-------------------+---------+------------------+----------+
 | 101       | Algorithms        | 3       | Computer Science | yes      |
 | 102       | Data Structures   | 3       | Computer Science | yes      |
 | 103       | Calculus          | 4       | Mathematics      | yes      |
 | 104       | Linear Algebra    | 4       | Mathematics      | yes      |
 | 105       | Machine Learning  | 3       | Computer Science | no       |
 | 106       | Probability       | 3       | Mathematics      | no       |
 | 107       | Operating Systems | 3       | Computer Science | no       |
 | 108       | Statistics        | 3       | Mathematics      | no       |
 +-----------+-------------------+---------+------------------+----------+
 </pre>

<p>enrollments 表：</p>

<pre class="example-io">
 +------------+-----------+-------------+-------+-----+
 | student_id | course_id | semester    | grade | GPA |
 +------------+-----------+-------------+-------+-----+
 | 1          | 101       | Fall 2023   | A     | 4.0 |
 | 1          | 102       | Spring 2023 | A     | 4.0 |
 | 1          | 105       | Spring 2023 | A     | 4.0 |
 | 1          | 107       | Fall 2023   | B     | 3.5 |
 | 2          | 101       | Fall 2023   | A     | 4.0 |
 | 2          | 102       | Spring 2023 | B     | 3.0 |
 | 3          | 103       | Fall 2023   | A     | 4.0 |
 | 3          | 104       | Spring 2023 | A     | 4.0 |
 | 3          | 106       | Spring 2023 | A     | 4.0 |
 | 3          | 108       | Fall 2023   | B     | 3.5 |
 | 4          | 103       | Fall 2023   | B     | 3.0 |
 | 4          | 104       | Spring 2023 | B     | 3.0 |
 +------------+-----------+-------------+-------+-----+
 </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
 +------------+
 | student_id |
 +------------+
 | 1          |
 | 3          |
 +------------+
 </pre>

<p><strong>解释：</strong></p>

<ul>
	<li>Alice (student_id 1) 是计算机科学专业并且修了&nbsp;Algorithms&nbsp;和 Data Structures，都取得了 A。她同时选修了&nbsp;Machine Learning&nbsp;和 Operating Systems，分别取得了 A 和 B。</li>
	<li>Bob (student_id 2) 是计算机科学专业但没有在所有需求的课程中取得 A。</li>
	<li>Charlie (student_id 3) 是数学专业并且修了 Calculus&nbsp;和 Linear Algebra，都取得了 A。他同时选修了&nbsp;Probability&nbsp;和 Statistics，分别取得了 A 和 B。</li>
	<li>David (student_id 4) 是数学专业但没有在所有需要的课程中取得 A。</li>
</ul>

<p><strong>注意：</strong>输出表以 student_id 升序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：连接 + 分组 + 条件过滤

<!-- thinking:start -->

> **思考**
>
> 除平均 GPA 外，还要求必修全 $A$、选修至少两门且均为 $A/B$。多层过滤若拆成多个查询，空成绩与未选课容易漏。
>
> 先用 CTE 筛出 GPA $\ge 2.5$ 的学生，再连专业课程并左连成绩，用条件求和表达三类约束。
>
> `HAVING` 比较必修 $A$ 数与必修门数、选修非空且成绩在 $A/B$、以及选修门数 $\ge 2$。左连让未选课的 $grade$ 为 `NULL`。

<!-- thinking:end -->

我们首先筛选出平均 GPA 大于等于 2.5 的学生，记录在 `T` 表中。

然后，我们将 `T` 表与 `students` 表按照 `student_id` 进行连接，然后与 `courses` 表按照 `major` 进行连接，再与 `enrollments` 表按照 `student_id` 和 `course_id` 进行左连接。

接下来，我们按照学生 ID 进行分组，然后使用 `HAVING` 子句过滤出符合条件的学生，最后按照学生 ID 进行排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT student_id
        FROM enrollments
        GROUP BY 1
        HAVING AVG(GPA) >= 2.5
    )
SELECT student_id
FROM
    T
    JOIN students USING (student_id)
    JOIN courses USING (major)
    LEFT JOIN enrollments USING (student_id, course_id)
GROUP BY 1
HAVING
    SUM(mandatory = 'yes' AND grade = 'A') = SUM(mandatory = 'yes')
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) = SUM(mandatory = 'no' AND grade IN ('A', 'B'))
    AND SUM(mandatory = 'no' AND grade IS NOT NULL) >= 2
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
