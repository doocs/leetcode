# [1873. 计算特殊奖金](https://leetcode.cn/problems/calculate-special-bonus)

[English Version](/solution/1800-1899/1873.Calculate%20Special%20Bonus/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表: <code>Employees</code></p>

<pre>
+-------------+---------+
| 列名        | 类型     |
+-------------+---------+
| employee_id | int     |
| name        | varchar |
| salary      | int     |
+-------------+---------+
employee_id 是这个表的主键。
此表的每一行给出了雇员id ，名字和薪水。
</pre>

<p>&nbsp;</p>

<p>写出一个SQL 查询语句，计算每个雇员的奖金。如果一个雇员的id是奇数并且他的名字不是以'M'开头，那么他的奖金是他工资的100%，否则奖金为0。</p>

<p>Return the result table ordered by <code>employee_id</code>.</p>

<p>返回的结果集请按照<code>employee_id</code>排序。</p>

<p>查询结果格式如下面的例子所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
Employees 表:
+-------------+---------+--------+
| employee_id | name    | salary |
+-------------+---------+--------+
| 2           | Meir    | 3000   |
| 3           | Michael | 3800   |
| 7           | Addilyn | 7400   |
| 8           | Juan    | 6100   |
| 9           | Kannon  | 7700   |
+-------------+---------+--------+
<strong>输出：</strong>
+-------------+-------+
| employee_id | bonus |
+-------------+-------+
| 2           | 0     |
| 3           | 0     |
| 7           | 7400  |
| 8           | 0     |
| 9           | 7700  |
+-------------+-------+
<strong>解释：</strong>
因为雇员id是偶数，所以雇员id 是2和8的两个雇员得到的奖金是0。
雇员id为3的因为他的名字以'M'开头，所以，奖金是0。
其他的雇员得到了百分之百的奖金。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```sql
SELECT
    employee_id,
    CASE
        WHEN employee_id % 2 = 0
        OR LEFT(name, 1) = 'M' THEN 0
        ELSE salary
    END AS bonus
FROM
    employees;
```

MySQL

```sql
SELECT
    employee_id,
    IF(
        employee_id % 2 = 0
        OR LEFT(name, 1) = 'M',
        0,
        salary
    ) AS bonus
FROM
    employees;
```

<!-- tabs:end -->
