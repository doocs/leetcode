# [1667. 修复表中的名字](https://leetcode.cn/problems/fix-names-in-a-table)

[English Version](/solution/1600-1699/1667.Fix%20Names%20in%20a%20Table/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表： <code>Users</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| name           | varchar |
+----------------+---------+
user_id 是该表的主键。
该表包含用户的 ID 和名字。名字仅由小写和大写字符组成。
</pre>

<p>&nbsp;</p>

<p>编写一个 SQL 查询来修复名字，使得只有第一个字符是大写的，其余都是小写的。</p>

<p>返回按 <code>user_id</code> 排序的结果表。</p>

<p>查询结果格式示例如下。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Users table:
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | aLice |
| 2       | bOB   |
+---------+-------+
<strong>输出：</strong>
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | Alice |
| 2       | Bob   |
+---------+-------+</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

MySQL

```sql
SELECT
    user_id,
    CONCAT(UPPER(LEFT(name, 1)), LOWER(SUBSTRING(name, 2))) AS name
FROM
    users
ORDER BY
    user_id;
```

SQL Server

```sql
SELECT
    user_id,
    CONCAT(
        UPPER(LEFT(name, 1)),
        LOWER(SUBSTRING(name, 2, DATALENGTH(name)))
    ) AS name
FROM
    users
ORDER BY
    user_id;
```

<!-- tabs:end -->
