---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3338.Second%20Highest%20Salary%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3338. 第二高的薪水 II 🔒](https://leetcode.cn/problems/second-highest-salary-ii)

[English Version](/solution/3300-3399/3338.Second%20Highest%20Salary%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>employees</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| emp_id           | int     |
| salary           | int     |
| dept             | varchar |
+------------------+---------+
emp_id 是这张表的唯一主键。
这张表的每一行包含雇员信息，包括他们的 ID，薪水和部门。
</pre>

<p>编写一个解决方案来找到每个部门中 <strong>薪水第二高</strong> 的雇员。如果 <strong>有多个雇员有第二高的薪水，在结果中包含所有获得该薪水的雇员</strong>。</p>

<p>返回结果表以&nbsp;<code>emp_id</code> <strong>升序&nbsp;</strong>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>employees 表：</p>

<pre class="example-io">
+--------+--------+-----------+
| emp_id | salary | dept      |
+--------+--------+-----------+
| 1      | 70000  | Sales     |
| 2      | 80000  | Sales     |
| 3      | 80000  | Sales     |
| 4      | 90000  | Sales     |
| 5      | 55000  | IT        |
| 6      | 65000  | IT        |
| 7      | 65000  | IT        |
| 8      | 50000  | Marketing |
| 9      | 55000  | Marketing |
| 10     | 55000  | HR        |
+--------+--------+-----------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+--------+-----------+
| emp_id | dept      |
+--------+-----------+
| 2      | Sales     |
| 3      | Sales     |
| 5      | IT        |
| 8      | Marketing |
+--------+-----------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><b>销售部门：</b>

    <ul>
    	<li>最高薪水为 90000 (emp_id: 4)</li>
    	<li>第二高的薪水为 80000 (emp_id: 2, 3)</li>
    	<li>两个薪水为 80000 的雇员都被包含</li>
    </ul>
    </li>
    <li><strong>IT 部门：</strong>
    <ul>
    	<li>最高薪水为 65000 (emp_id: 6, 7)</li>
    	<li>第二高的薪水为 55000 (emp_id: 5)</li>
    	<li>只有 emp_id 为 5 的雇员被包含，因为他的薪水第二高</li>
    </ul>
    </li>
    <li><b>市场部门：</b>
    <ul>
    	<li>最高薪水为 55000 (emp_id: 9)</li>
    	<li>第二高的薪水为 50000 (emp_id: 8)</li>
    	<li>雇员 8 被包含</li>
    </ul>
    </li>
    <li><b>人力资源部门：</b>
    <ul>
    	<li>只有一个雇员</li>
    	<li>因为少于 2 个雇员，所以没有包含在结果中</li>
    </ul>
    </li>

</ul>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：窗口函数

我们可以使用 `DENSE_RANK()` 窗口函数来为每个部门的员工按照工资降序排名，然后筛选出排名为 $2$ 的员工即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            emp_id,
            dept,
            DENSE_RANK() OVER (
                PARTITION BY dept
                ORDER BY salary DESC
            ) rk
        FROM Employees
    )
SELECT emp_id, dept
FROM T
WHERE rk = 2
ORDER BY 1;
```

#### Pandas

```python
import pandas as pd


def find_second_highest_salary(employees: pd.DataFrame) -> pd.DataFrame:
    employees["rk"] = employees.groupby("dept")["salary"].rank(
        method="dense", ascending=False
    )
    second_highest = employees[employees["rk"] == 2][["emp_id", "dept"]]
    return second_highest.sort_values(by="emp_id")
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
