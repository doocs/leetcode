---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2004. 职员招聘人数 🔒](https://leetcode.cn/problems/the-number-of-seniors-and-juniors-to-join-the-company)

[English Version](/solution/2000-2099/2004.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company/README_EN.md)

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
employee_id是此表的主键列。
经验是包含一个值（“高级”、“初级”）的枚举类型。
此表的每一行都显示候选人的id、月薪和经验。</pre>

<p>&nbsp;</p>

<p>一家公司想雇佣新员工。公司的工资预算是 <code>70000</code> 美元。公司的招聘标准是：</p>

<ol>
	<li>雇佣最多的高级员工。</li>
	<li>在雇佣最多的高级员工后，使用剩余预算雇佣最多的初级员工。</li>
</ol>

<p>编写一个SQL查询，查找根据上述标准雇佣的高级员工和初级员工的数量。<br />
按 <strong>任意顺序</strong> 返回结果表。<br />
查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 20000  |
| 11          | Senior     | 20000  |
| 13          | Senior     | 50000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 2                   |
| Junior     | 2                   |
+------------+---------------------+
<strong>说明：
我们可以雇佣2名ID为（2,11）的高级员工。由于预算是7万美元，他们的工资总额是4万美元，我们还有3万美元，但他们不足以雇佣ID为13的高级员工。
我们可以雇佣2名ID为（1,9）的初级员工。由于剩下的预算是3万美元，他们的工资总额是2万美元，我们还有1万美元，但他们不足以雇佣ID为4的初级员工。
</strong></pre>

<strong>示例 2：</strong>

<pre>
<strong>输入:</strong> 
Candidates table:
+-------------+------------+--------+
| employee_id | experience | salary |
+-------------+------------+--------+
| 1           | Junior     | 10000  |
| 9           | Junior     | 10000  |
| 2           | Senior     | 80000  |
| 11          | Senior     | 80000  |
| 13          | Senior     | 80000  |
| 4           | Junior     | 40000  |
+-------------+------------+--------+
<strong>输出:</strong> 
+------------+---------------------+
| experience | accepted_candidates |
+------------+---------------------+
| Senior     | 0                   |
| Junior     | 3                   |
+------------+---------------------+
<strong>解释：
</strong>我们不能用目前的预算雇佣任何高级员工，因为我们需要至少80000美元来雇佣一名高级员工。
我们可以用剩下的预算雇佣三名初级员工。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

<!-- thinking:start -->

> **思考**
>
> 预算固定为 $70000$，且 Senior 优先于 Junior。若枚举子集则人数稍大即不可行。在同类内部应按工资升序录取，才能在预算内最大化人数。
>
> 窗口函数按工资求前缀和 $cur$，即「录取到该职员时的累计支出」。Senior 中 $cur \le 70000$ 的人数即为录取人数；Junior 的前缀和还须叠加上 Senior 已用额度。
>
> 因此用两个 CTE 分别计算两类累计工资，再 `UNION ALL` 统计不超过预算的职员数。

<!-- thinking:end -->

相似题目：

- [2010. 职员招聘人数 🔒 II](https://github.com/doocs/leetcode/blob/main/solution/2000-2099/2010.The%20Number%20of%20Seniors%20and%20Juniors%20to%20Join%20the%20Company%20II/README.md)

<!-- tabs:start -->

#### MySQL

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
    'Senior' AS experience,
    COUNT(employee_id) AS accepted_candidates
FROM s
WHERE cur <= 70000
UNION ALL
SELECT
    'Junior' AS experience,
    COUNT(employee_id) AS accepted_candidates
FROM j
WHERE cur <= 70000;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
