---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2668.Find%20Latest%20Salaries/README.md
tags:
    - 数据库
---

# [2668. 查询员工当前薪水 🔒](https://leetcode.cn/problems/find-latest-salaries)

[English Version](/solution/2600-2699/2668.Find%20Latest%20Salaries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code><font face="monospace">Salary</font></code></p>

<pre>
+---------------+---------+ 
| 列名          | 类型    | 
+---------------+---------+ 
| emp_id        | int     | 
| firstname     | varchar |
| lastname      | varchar |
| salary        | varchar |
| department_id | varchar |
+---------------+---------+
(emp_id, salary) 是该表的主键(具有唯一值的列的组合)。
每行包含员工的详细信息和他们每年的薪水，但有些记录是旧的，包含过时的薪资信息。
</pre>

<p>找出每个员工的当前薪水，假设薪水每年增加。输出他们的 <code>emp_id</code>&nbsp;、<code>firstname</code>&nbsp;、<code>lastname</code>&nbsp;、<code>salary</code> 和 <code>department_id</code> 。</p>

<p>按&nbsp;<code>emp_id</code> <strong>升序排序</strong> 返回结果表。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：
</strong><code>Salary</code> 表:
+--------+-----------+----------+--------+---------------+
| emp_id | firstname | lastname | salary | department_id |
+--------+-----------+----------+--------+---------------+ 
| 1      | Todd      | Wilson   | 110000 | D1006         |
| 1      | Todd      | Wilson   | 106119 | D1006         | 
| 2      | Justin    | Simon    | 128922 | D1005         | 
| 2      | Justin    | Simon    | 130000 | D1005         | 
| 3      | Kelly     | Rosario  | 42689  | D1002         | 
| 4      | Patricia  | Powell   | 162825 | D1004         |
| 4      | Patricia  | Powell   | 170000 | D1004         |
| 5      | Sherry    | Golden   | 44101  | D1002         | 
| 6      | Natasha   | Swanson  | 79632  | D1005         | 
| 6      | Natasha   | Swanson  | 90000  | D1005         |
+--------+-----------+----------+--------+---------------+
<strong>输出：
</strong>+--------+-----------+----------+--------+---------------+
| emp_id | firstname | lastname | salary | department_id |
+--------+-----------+----------+--------+---------------+ 
| 1      | Todd      | Wilson   | 110000 | D1006         |
| 2      | Justin    | Simon    | 130000 | D1005         | 
| 3      | Kelly     | Rosario  | 42689  | D1002         | 
| 4      | Patricia  | Powell   | 170000 | D1004         |
| 5      | Sherry    | Golden   | 44101  | D1002         | 
| 6      | Natasha   | Swanson  | 90000  | D1005         |
+--------+-----------+----------+--------+---------------+<strong>
</strong>
<strong>解释：</strong>
- emp_id 1 有两条记录，工资分别为 110000 和 106119，其中 110000 是更新后的工资（假设工资每年都会增加）
- emp_id 2 有两条记录，工资分别为 128922 和 130000，其中 130000 是更新后的工资。
- emp_id 3 只有一条工资记录，因此这已经是更新后的工资。
- emp_id 4 有两条记录，工资分别为 162825 和 170000，其中 170000 是更新后的工资。
- emp_id 5 只有一条工资记录，因此这已经是更新后的工资。
- emp_id 6 有两条记录，工资分别为 79632 和 90000，其中 90000 是更新后的工资。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    emp_id,
    firstname,
    lastname,
    MAX(salary) AS salary,
    department_id
FROM Salary
GROUP BY emp_id
ORDER BY emp_id;
```

<!-- tabs:end -->

<!-- end -->
