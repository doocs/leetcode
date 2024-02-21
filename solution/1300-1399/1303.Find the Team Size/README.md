# [1303. 求团队人数](https://leetcode.cn/problems/find-the-team-size)

[English Version](/solution/1300-1399/1303.Find%20the%20Team%20Size/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>员工表：<code>Employee</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| employee_id   | int     |
| team_id       | int     |
+---------------+---------+
employee_id 字段是这张表的主键(具有唯一值的列)
表中的每一行都包含每个员工的 ID 和他们所属的团队。
</pre>

<p>&nbsp;</p>

<p>编写解决方案以求得每个员工所在团队的总人数。</p>

<p>返回结果表 <strong>无顺序要求&nbsp;</strong>。</p>

<p>返回结果格式示例如下：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employee Table:
+-------------+------------+
| employee_id | team_id    |
+-------------+------------+
|     1       |     8      |
|     2       |     8      |
|     3       |     8      |
|     4       |     7      |
|     5       |     9      |
|     6       |     9      |
+-------------+------------+
<strong>输出：</strong>
+-------------+------------+
| employee_id | team_size  |
+-------------+------------+
|     1       |     3      |
|     2       |     3      |
|     3       |     3      |
|     4       |     1      |
|     5       |     2      |
|     6       |     2      |
+-------------+------------+
<strong>解释：</strong>
ID 为 1、2、3 的员工是 team_id 为 8 的团队的成员，
ID 为 4 的员工是 team_id 为 7 的团队的成员，
ID 为 5、6 的员工是 team_id 为 9 的团队的成员。
</pre>

## 解法

### 方法一：分组统计 + 等值连接

我们可以先统计出每个团队的人数，记录在 `T` 表中，然后我们将 `Employee` 表与 `T` 表按照 `team_id` 进行等值连接，即可得到每个员工所在团队的总人数。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT team_id, COUNT(1) AS team_size
        FROM Employee
        GROUP BY 1
    )
SELECT employee_id, team_size
FROM
    Employee
    JOIN T USING (team_id);
```

<!-- tabs:end -->

### 方法二：左连接

我们也可以使用左连接，将 `Employee` 表按照 `team_id` 进行自连接，然后按照 `employee_id` 进行分组，统计每个员工所在团队的总人数。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT e1.employee_id, COUNT(1) AS team_size
FROM
    Employee AS e1
    LEFT JOIN Employee AS e2 USING (team_id)
GROUP BY 1;
```

<!-- tabs:end -->

<!-- end -->
