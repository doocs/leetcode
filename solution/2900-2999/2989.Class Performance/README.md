# [2989. 班级表现 🔒](https://leetcode.cn/problems/class-performance)

[English Version](/solution/2900-2999/2989.Class%20Performance/README_EN.md)

<!-- tags:数据库 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Scores</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| student_id   | int     |
| student_name | varchar |
| assignment1  | int     |
| assignment2  | int     |
| assignment3  | int     |
+--------------+---------+
student_id 是这张表具有唯一值的列。
该表包含 student_id, student_name, assignment1, assignment2,和 assignment3。
</pre>

<p>编写一个查询，计算学生获得的&nbsp;<strong>最高总分&nbsp;</strong>和&nbsp;<strong>最低总分&nbsp;</strong>之间的&nbsp;<strong>差</strong>（<code>3</code> 次作业的总和）。</p>

<p>以 <em><strong>任意</strong> 顺序返回结果表。</em></p>

<p>结果表的格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Scores 表：
+------------+--------------+-------------+-------------+-------------+
| student_id | student_name | assignment1 | assignment2 | assignment3 |
+------------+--------------+-------------+-------------+-------------+
| 309        | Owen         | 88          | 47          | 87          |
| 321        | Claire       | 98          | 95          | 37          |     
| 338        | Julian       | 100         | 64          | 43          |  
| 423        | Peyton       | 60          | 44          | 47          |  
| 896        | David        | 32          | 37          | 50          | 
| 235        | Camila       | 31          | 53          | 69          | 
+------------+--------------+-------------+-------------+-------------+
<b>输出</b>
+---------------------+
| difference_in_score | 
+---------------------+
| 111                 | 
+---------------------+
<b>解释</b>
- student_id 309 的总分为 88 + 47 + 87 = 222。
- student_id 321 的总分为 98 + 95 + 37 = 230。
- student_id 338 的总分为 100 + 64 + 43 = 207。
- student_id 423 的总分为 60 + 44 + 47 = 151。
- student_id 896 的总分为 32 + 37 + 50 = 119。
- student_id 235 的总分为 31 + 53 + 69 = 153。
student_id 321 拥有最高分为 230，而 student_id 896 拥有最低分为 119。因此，它们之间的差异为 111。
</pre>

## 解法

### 方法一：最大值最小值

我们可以使用 `MAX` 和 `MIN` 函数来分别获取 `assignment1`、`assignment2`、`assignment3` 的和的最大值和最小值，然后相减即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    MAX(assignment1 + assignment2 + assignment3) - MIN(
        assignment1 + assignment2 + assignment3
    ) AS difference_in_score
FROM Scores;
```

<!-- tabs:end -->

<!-- end -->
