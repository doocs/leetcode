# [2356. 每位教师所教授的科目种类的数量](https://leetcode.cn/problems/number-of-unique-subjects-taught-by-each-teacher)

[English Version](/solution/2300-2399/2356.Number%20of%20Unique%20Subjects%20Taught%20by%20Each%20Teacher/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Teacher</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| teacher_id  | int  |
| subject_id  | int  |
| dept_id     | int  |
+-------------+------+
在 SQL 中，(subject_id, dept_id) 是该表的主键。
该表中的每一行都表示带有 teacher_id 的教师在系 dept_id 中教授科目 subject_id。
</pre>

<p>&nbsp;</p>

<p>查询每位老师在大学里教授的科目种类的数量。</p>

<p data-group="1-1">以 <strong>任意顺序</strong> 返回结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Teacher 表:
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
<strong>输出:</strong>  
+------------+-----+
| teacher_id | cnt |
+------------+-----+
| 1          | 2   |
| 2          | 4   |
+------------+-----+
<strong>解释:</strong> 
教师 1:
  - 他在 3、4 系教科目 2。
  - 他在 3 系教科目 3。
教师 2:
  - 他在 1 系教科目 1。
  - 他在 1 系教科目 2。
  - 他在 1 系教科目 3。
  - 他在 1 系教科目 4。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt
FROM Teacher
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
