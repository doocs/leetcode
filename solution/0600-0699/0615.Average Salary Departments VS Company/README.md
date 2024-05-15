---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0615.Average%20Salary%20Departments%20VS%20Company/README.md
tags:
    - 数据库
---

# [615. 平均工资：部门与公司比较 🔒](https://leetcode.cn/problems/average-salary-departments-vs-company)

[English Version](/solution/0600-0699/0615.Average%20Salary%20Departments%20VS%20Company/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Salary</code></p>

<pre>
+-------------+------+
| 列名        | 类型 |
+-------------+------+
| id          | int  |
| employee_id | int  |
| amount      | int  |
| pay_date    | date |
+-------------+------+
在 SQL 中，id 是该表的主键列。
该表的每一行表示一个员工一个月的薪资。
employee_id 是来自 Employee 表的外键（reference 列）。</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Employee</code></p>

<pre>
+---------------+------+
| 列名          | 类型 |
+---------------+------+
| employee_id   | int  |
| department_id | int  |
+---------------+------+
在 SQL 中，employee_id 是该表的主键列。
该表的每一行表示一个员工所属的部门。</pre>

<p>&nbsp;</p>

<p>找出各个部门员工的平均薪资与公司平均薪资之间的比较结果（<strong>更高 / 更低 / 相同</strong>）。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Salary 表:
+----+-------------+--------+------------+
| id | employee_id | amount | pay_date   |
+----+-------------+--------+------------+
| 1  | 1           | 9000   | 2017/03/31 |
| 2  | 2           | 6000   | 2017/03/31 |
| 3  | 3           | 10000  | 2017/03/31 |
| 4  | 1           | 7000   | 2017/02/28 |
| 5  | 2           | 6000   | 2017/02/28 |
| 6  | 3           | 8000   | 2017/02/28 |
+----+-------------+--------+------------+
Employee 表:
+-------------+---------------+
| employee_id | department_id |
+-------------+---------------+
| 1           | 1             |
| 2           | 2             |
| 3           | 2             |
+-------------+---------------+
<strong>输出：</strong>
+-----------+---------------+------------+
| pay_month | department_id | comparison |
+-----------+---------------+------------+
| 2017-02   | 1             | same       |
| 2017-03   | 1             | higher     |
| 2017-02   | 2             | same       |
| 2017-03   | 2             | lower      |
+-----------+---------------+------------+
<strong>解释：
</strong>在三月，公司的平均工资是 (9000+6000+10000)/3 = 8333.33...
部门 '1' 的平均薪资是 9000，因为该部门只有一个员工，其员工号为 '1'。因为 9000 &gt; 8333.33，所以比较结果为 'higher'
部门 '2' 的平均薪资是（6000 + 10000）/ 2 = 8000，该平均薪资是员工号 '2' 和 '3' 的薪资的平均值。因为 8000 &lt; 8333.33，比较结果为 'lower'。

根据同样的公式，对于二月份的平均薪资比较，结果为 'same'，因为部门 '1' 和 '2' 都与公司的平均薪资相同，即为 7000。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            DATE_FORMAT(pay_date, '%Y-%m') AS pay_month,
            department_id,
            AVG(amount) OVER (PARTITION BY pay_date) AS company_avg_amount,
            AVG(amount) OVER (PARTITION BY pay_date, department_id) AS department_avg_amount
        FROM
            Salary AS s
            JOIN Employee AS e ON s.employee_id = e.employee_id
    )
SELECT DISTINCT
    pay_month,
    department_id,
    CASE
        WHEN company_avg_amount = department_avg_amount THEN 'same'
        WHEN company_avg_amount < department_avg_amount THEN 'higher'
        ELSE 'lower'
    END AS comparison
FROM t;
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT *
        FROM
            Salary
            JOIN Employee USING (employee_id)
    ),
    T AS (
        SELECT
            DATE_FORMAT(pay_date, '%Y-%m') AS pay_month,
            department_id,
            AVG(amount) OVER (PARTITION BY pay_date, department_id) AS department_avg,
            AVG(amount) OVER (PARTITION BY pay_date) AS company_avg
        FROM S
    )
SELECT
    pay_month,
    department_id,
    CASE
        WHEN AVG(department_avg) > AVG(company_avg) THEN 'higher'
        WHEN AVG(department_avg) < AVG(company_avg) THEN 'lower'
        ELSE 'same'
    END AS comparison
FROM T
GROUP BY 1, 2;
```

<!-- tabs:end -->

<!-- end -->
