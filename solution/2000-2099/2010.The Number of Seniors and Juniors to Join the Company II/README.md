---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2010.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2010. 职员招聘人数 II 🔒](https://leetcode.cn/problems/the-number-of-seniors-and-juniors-to-join-the-company-ii)

[English Version](/solution/2000-2099/2010.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表: <code>Candidates</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| employee_id | int  |
| experience  | enum |
| salary      | int  |
+-------------+------+
employee_id 是该表中具有唯一值的列。
经验是一个枚举，其中包含一个值（“高级”、“初级”）。
此表的每一行都显示候选人的id、月薪和经验。
每个候选人的工资保证是 <strong>唯一</strong> 的。</pre>

<p>&nbsp;</p>

<p>一家公司想雇佣新员工。公司的工资预算是 <code>$70000</code> 。公司的招聘标准是：</p>

<ol>
	<li>继续雇佣薪水最低的高级职员，直到你不能再雇佣更多的高级职员。</li>
	<li>用剩下的预算雇佣薪水最低的初级职员。</li>
	<li>继续以最低的工资雇佣初级职员，直到你不能再雇佣更多的初级职员。</li>
</ol>

<p>编写一个解决方案，查找根据上述条件雇用职员的 ID。<br />
按 <strong>任意顺序 </strong>返回结果表。<br />
返回结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 15000  |
| 2           | Senior     | 20000  |
| 11          | Senior     | 16000  |
| 13          | Senior     | 50000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+-------------+
| employee_id |
+-------------+
| 11          |
| 2           |
| 1           |
| 9           |
+-------------+
<strong>解释:</strong> 
我们可以雇佣2名具有ID（11,2）的高级员工。由于预算是7万美元，他们的工资总额是3.6万美元，我们还有3.4万美元，但他们不足以雇佣ID为 13 的高级职员。
我们可以雇佣2名ID为（1,9）的初级员工。由于剩余预算为3.4万美元，他们的工资总额为2.5万美元，我们还有9000美元，但他们不足以雇佣ID为 4 的初级员工。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 25000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 85000  |
| 11          | Senior     | 80000  |
| 13          | Senior     | 90000  |
| 4           | Junior     | 30000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+-------------+
| employee_id |
+-------------+
| 9           |
| 1           |
| 4           |
+-------------+
<strong>解释:</strong> 
我们不能用目前的预算雇佣任何高级员工，因为我们需要至少 80000 美元来雇佣一名高级员工。
我们可以用剩下的预算雇佣三名初级员工。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

相似题目：

-   [2004. 职员招聘人数](https://github.com/doocs/leetcode/blob/main/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README.md)

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    s AS (
        SELECT
            employee_id,
            SUM(salary) OVER (ORDER BY salary) AS cur
        FROM Candidates
        WHERE experience = 'Senior'
    ),
    j AS (
        SELECT
            employee_id,
            IFNULL(
                SELECT
                    MAX(cur)
                FROM s
                WHERE cur <= 70000,
                0
            ) + SUM(salary) OVER (ORDER BY salary) AS cur
        FROM Candidates
        WHERE experience = 'Junior'
    )
SELECT
    employee_id
FROM s
WHERE cur <= 70000
UNION
SELECT
    employee_id
FROM j
WHERE cur <= 70000;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
