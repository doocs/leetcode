# [2356. Number of Unique Subjects Taught by Each Teacher](https://leetcode.cn/problems/number-of-unique-subjects-taught-by-each-teacher)

[English Version](/solution/2300-2399/2356.Number%20of%20Unique%20Subjects%20Taught%20by%20Each%20Teacher/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Teacher</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| teacher_id  | int  |
| subject_id  | int  |
| dept_id     | int  |
+-------------+------+
(subject_id, dept_id) is the primary key for this table.
Each row in this table indicates that the teacher with teacher_id teaches the subject subject_id in the department dept_id.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the number of unique subjects each teacher teaches in the university.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Teacher table:
+------------+------------+---------+
| teacher_id | subject_id | dept_id |
+------------+------------+---------+
| 1          | 2          | 3       |
| 1          | 2          | 4       |
| 1          | 3          | 3       |
| 2          | 1          | 1       |
| 2          | 2          | 1       |
| 2          | 3          | 1       |
| 2          | 4          | 1       |
+------------+------------+---------+
<strong>Output:</strong>  
+------------+-----+
| teacher_id | cnt |
+------------+-----+
| 1          | 2   |
| 2          | 4   |
+------------+-----+
<strong>Explanation:</strong> 
Teacher 1:
  - They teach subject 2 in departments 3 and 4.
  - They teach subject 3 in department 3.
Teacher 2:
  - They teach subject 1 in department 1.
  - They teach subject 2 in department 1.
  - They teach subject 3 in department 1.
  - They teach subject 4 in department 1.
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql

```

<!-- tabs:end -->
