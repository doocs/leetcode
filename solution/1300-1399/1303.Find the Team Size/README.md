# [1303. 求团队人数](https://leetcode.cn/problems/find-the-team-size)

[English Version](/solution/1300-1399/1303.Find%20the%20Team%20Size/README_EN.md)

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
employee_id 字段是这张表的主键，表中的每一行都包含每个员工的 ID 和他们所属的团队。
</pre>

<p>编写一个 SQL 查询，以求得每个员工所在团队的总人数。</p>

<p>查询结果中的顺序无特定要求。</p>

<p>查询结果格式示例如下：</p>

<pre>
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
Result table:
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
ID 为 1、2、3 的员工是 team_id 为 8 的团队的成员，
ID 为 4 的员工是 team_id 为 7 的团队的成员，
ID 为 5、6 的员工是 team_id 为 9 的团队的成员。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

解法 1：

```sql
# Write your MySQL query statement below
SELECT
    e.employee_id, t.team_size
FROM
    Employee e
LEFT JOIN
    (SELECT
        team_id, count(1) as team_size
    FROM
        Employee
    GROUP BY
        team_id
    ) t
ON
    e.team_id = t.team_id;
```

解法 2：

```sql
# Write your MySQL query statement below
SELECT
    e1.employee_id, count(*) as team_size
FROM
    Employee e1
LEFT JOIN
    Employee e2
ON
    e1.team_id = e2.team_id
GROUP BY
    e1.employee_id;
```

<!-- tabs:end -->
