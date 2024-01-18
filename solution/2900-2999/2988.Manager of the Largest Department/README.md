# [2988. 最大部门的经理](https://leetcode.cn/problems/manager-of-the-largest-department)

[English Version](/solution/2900-2999/2988.Manager%20of%20the%20Largest%20Department/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：&nbsp;<code>Employees</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| emp_id      | int     |
| emp_name    | varchar |
| dep_id      | int     |
| position    | varchar |
+-------------+---------+
emp_id 是这张表具有唯一值的列。
这张表包括 emp_id, emp_name, dep_id,和 position。
</pre>

<p>查询 <strong>最大部门</strong> 的&nbsp;<strong>经理</strong>&nbsp;的&nbsp;<strong>名字</strong>。当拥有相同数量的员工时，可能会有多个最大部门。</p>

<p>返回&nbsp;<em>按照</em> <code>dep_id</code> <em><strong>升序</strong> 排列的结果表格。</em></p>

<p>结果表格的格式如下例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b>
Employees table:
+--------+----------+--------+---------------+
| emp_id | emp_name | dep_id | position      | 
+--------+----------+--------+---------------+
| 156    | Michael  | 107    | Manager       |
| 112    | Lucas    | 107    | Consultant    |    
| 8      | Isabella | 101    | Manager       | 
| 160    | Joseph   | 100    | Manager       | 
| 80     | Aiden    | 100    | Engineer      | 
| 190    | Skylar   | 100    | Freelancer    | 
| 196    | Stella   | 101    | Coordinator   |
| 167    | Audrey   | 100    | Consultant    |
| 97     | Nathan   | 101    | Supervisor    |
| 128    | Ian      | 101    | Administrator |
| 81     | Ethan    | 107    | Administrator |
+--------+----------+--------+---------------+
<b>输出</b>
+--------------+--------+
| manager_name | dep_id | 
+--------------+--------+
| Joseph       | 100    | 
| Isabella     | 101    | 
+--------------+--------+
<b>解释</b>
- 部门 ID 为 100 和 101 的每个部门都有 4 名员工，而部门 107 有 3 名员工。由于部门 100 和 101 都拥有相同数量的员工，它们各自的经理将被包括在内。
输出表格按 dep_id 升序排列。

</pre>

## 解法

### 方法一：分组 + 等值连接 + 子查询

我们可以先统计每个部门的员工数量，记为表 `T`，然后我们将 `T` 与 `Employees` 表进行连接，连接条件为 `T.dep_id = Employees.dep_id`，并且 `Employees.position = 'Manager'`，这样就可以得到每个部门的经理，最后我们再筛选出员工数量最多的部门即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT dep_id, COUNT(1) AS cnt
        FROM Employees
        GROUP BY 1
    )
SELECT emp_name AS manager_name, t.dep_id
FROM
    T AS t
    JOIN Employees AS e ON t.dep_id = e.dep_id AND e.position = 'Manager'
WHERE cnt = (SELECT MAX(cnt) FROM T)
ORDER BY 2;
```

<!-- tabs:end -->

<!-- end -->
