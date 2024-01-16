# [1468. 计算税后工资](https://leetcode.cn/problems/calculate-salaries)

[English Version](/solution/1400-1499/1468.Calculate%20Salaries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><code>Salaries</code> 表：</p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| company_id    | int     |
| employee_id   | int     |
| employee_name | varchar |
| salary        | int     |
+---------------+---------+
在 SQL 中，(company_id, employee_id) 是这个表的主键
这个表包括员工的company id, id, name 和 salary 
</pre>

<p>&nbsp;</p>

<p>查找出每个员工的税后工资</p>

<p>每个公司的税率计算依照以下规则</p>

<ul>
	<li>如果这个公司员工最高工资不到 <code>$1000</code> ，税率为 <code>0%</code></li>
	<li>如果这个公司员工最高工资在 <code>[1000, 10000]</code> 之间，税率为 <code>24%</code></li>
	<li>如果这个公司员工最高工资大于 <code>$10000</code> ，税率为 <code>49%</code></li>
</ul>

<p>按 <strong>任意顺序</strong> 返回结果。</p>

<p>返回结果的格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Salaries 表：
+------------+-------------+---------------+--------+
| company_id | employee_id | employee_name | salary |
+------------+-------------+---------------+--------+
| 1          | 1           | Tony          | 2000   |
| 1          | 2           | Pronub        | 21300  |
| 1          | 3           | Tyrrox        | 10800  |
| 2          | 1           | Pam           | 300    |
| 2          | 7           | Bassem        | 450    |
| 2          | 9           | Hermione      | 700    |
| 3          | 7           | Bocaben       | 100    |
| 3          | 2           | Ognjen        | 2200   |
| 3          | 13          | Nyancat       | 3300   |
| 3          | 15          | Morninngcat   | 7777   |
+------------+-------------+---------------+--------+
<strong>输出：</strong>
+------------+-------------+---------------+--------+
| company_id | employee_id | employee_name | salary |
+------------+-------------+---------------+--------+
| 1          | 1           | Tony          | 1020   |
| 1          | 2           | Pronub        | 10863  |
| 1          | 3           | Tyrrox        | 5508   |
| 2          | 1           | Pam           | 300    |
| 2          | 7           | Bassem        | 450    |
| 2          | 9           | Hermione      | 700    |
| 3          | 7           | Bocaben       | 76     |
| 3          | 2           | Ognjen        | 1672   |
| 3          | 13          | Nyancat       | 2508   |
| 3          | 15          | Morninngcat   | 5911   |
+------------+-------------+---------------+--------+
<strong>解释：</strong>
对于公司 1，最高薪资为 21300。公司 1 的员工税率为 49%。
对于公司 2，最高薪资为 700。公司 2 的员工税率为 0%。
对于公司 3，最高薪资为 7777。公司 3 的员工税率为 24%。
薪资扣除税后的金额计算公式为：薪资 - (税率百分比 / 100) * 薪资
例如，Morninngcat（员工号 3，薪资为 7777）扣除税后的薪资为：7777 - 7777 * (24 / 100) = 7777 - 1866.48 = 5910.52，四舍五入为 5911。</pre>

## 解法

### 方法一

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    s.company_id,
    employee_id,
    employee_name,
    ROUND(
        CASE
            WHEN top < 1000 THEN salary
            WHEN top >= 1000
            AND top <= 10000 THEN salary * 0.76
            ELSE salary * 0.51
        END
    ) AS salary
FROM
    Salaries AS s
    JOIN (
        SELECT company_id, MAX(salary) AS top
        FROM Salaries
        GROUP BY company_id
    ) AS t
        ON s.company_id = t.company_id;
```

<!-- tabs:end -->

<!-- end -->
