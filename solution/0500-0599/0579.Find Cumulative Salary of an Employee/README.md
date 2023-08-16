# [579. 查询员工的累计薪水](https://leetcode.cn/problems/find-cumulative-salary-of-an-employee)

[English Version](/solution/0500-0599/0579.Find%20Cumulative%20Salary%20of%20an%20Employee/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| month       | int  |
| salary      | int  |
+-------------+------+
(id, month) 是该表的主键(具有唯一值的列的组合)。
表中的每一行表示 2020 年期间员工一个月的工资。
</pre>

<p>&nbsp;</p>

<p>编写一个解决方案，在一个统一的表中计算出每个员工的 <strong>累计工资汇总</strong> 。</p>

<p>员工的 <strong>累计工资汇总</strong> 可以计算如下:</p>

<ul>
	<li>对于该员工工作的每个月，将 <strong>该月</strong> 和 <strong>前两个月</strong> 的工资 <strong>加</strong> 起来。这是他们当月的 <strong>3 个月总工资</strong><strong>和</strong> 。如果员工在前几个月没有为公司工作，那么他们在前几个月的有效工资为 <code>0</code> 。</li>
	<li><strong>不要</strong> 在摘要中包括员工 <strong>最近一个月</strong> 的 3 个月总工资和。</li>
	<li><strong>不要</strong> 包括雇员 <strong>没有工作</strong> 的任何一个月的 3 个月总工资和。</li>
</ul>

<p>返回按 <code>id</code> <strong>升序排序&nbsp;</strong>的结果表。如果 <code>id</code> 相等，请按 <code>month</code> <strong>降序排序</strong>。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1</strong></p>

<pre>
<b>输入：</b>
Employee table:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 1  | 1     | 20     |
| 2  | 1     | 20     |
| 1  | 2     | 30     |
| 2  | 2     | 30     |
| 3  | 2     | 40     |
| 1  | 3     | 40     |
| 3  | 3     | 60     |
| 1  | 4     | 60     |
| 3  | 4     | 70     |
| 1  | 7     | 90     |
| 1  | 8     | 90     |
+----+-------+--------+
<b>输出：</b>
+----+-------+--------+
| id | month | Salary |
+----+-------+--------+
| 1  | 7     | 90     |
| 1  | 4     | 130    |
| 1  | 3     | 90     |
| 1  | 2     | 50     |
| 1  | 1     | 20     |
| 2  | 1     | 20     |
| 3  | 3     | 100    |
| 3  | 2     | 40     |
+----+-------+--------+
<b>解释：</b>
员工 “1” 有 5 条工资记录，不包括最近一个月的 “8”:
- 第 '7' 个月为 90。
- 第 '4' 个月为 60。
- 第 '3' 个月是 40。
- 第 '2' 个月为 30。
- 第 '1' 个月为 20。
因此，该员工的累计工资汇总为:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 1  | 7     | 90     |  (90 + 0 + 0)
| 1  | 4     | 130    |  (60 + 40 + 30)
| 1  | 3     | 90     |  (40 + 30 + 20)
| 1  | 2     | 50     |  (30 + 20 + 0)
| 1  | 1     | 20     |  (20 + 0 + 0)
+----+-------+--------+
请注意，'7' 月的 3 个月的总和是 90，因为他们没有在 '6' 月或 '5' 月工作。

员工 '2' 只有一个工资记录('1' 月)，不包括最近的 '2' 月。
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 2  | 1     | 20     |  (20 + 0 + 0)
+----+-------+--------+

员工 '3' 有两个工资记录，不包括最近一个月的 '4' 月:
- 第 '3' 个月为 60 。
- 第 '2' 个月是 40。
因此，该员工的累计工资汇总为:
+----+-------+--------+
| id | month | salary |
+----+-------+--------+
| 3  | 3     | 100    |  (60 + 40 + 0)
| 3  | 2     | 40     |  (40 + 0 + 0)
+----+-------+--------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    id,
    month,
    sum(salary) OVER (
        PARTITION BY id
        ORDER BY month
        RANGE 2 PRECEDING
    ) AS Salary
FROM employee
WHERE
    (id, month) NOT IN (
        SELECT
            id,
            max(month)
        FROM Employee
        GROUP BY id
    )
ORDER BY id, month DESC;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            id,
            month,
            sum(salary) OVER (
                PARTITION BY id
                ORDER BY month
                RANGE 2 PRECEDING
            ) AS salary,
            rank() OVER (
                PARTITION BY id
                ORDER BY month DESC
            ) AS rk
        FROM Employee
    )
SELECT id, month, salary
FROM T
WHERE rk > 1
ORDER BY 1, 2 DESC;
```

<!-- tabs:end -->
