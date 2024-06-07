---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0603.Consecutive%20Available%20Seats/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [603. 连续空余座位 🔒](https://leetcode.cn/problems/consecutive-available-seats)

[English Version](/solution/0600-0699/0603.Consecutive%20Available%20Seats/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表:&nbsp;<code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
Seat_id 是该表的自动递增主键列。
在 PostgreSQL 中，<code>free</code> 存储为整数。请使用 <code>::boolean</code> 将其转换为布尔格式。
该表的每一行表示第 i 个座位是否空闲。1 表示空闲，0 表示被占用。</pre>

<p>&nbsp;</p>

<p>查找电影院所有连续可用的座位。</p>

<p>返回按 <code>seat_id</code> <strong>升序排序&nbsp;</strong>的结果表。</p>

<p>测试用例的生成使得两个以上的座位连续可用。</p>

<p>结果表格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Cinema 表:
+---------+------+
| seat_id | free |
+---------+------+
| 1       | 1    |
| 2       | 0    |
| 3       | 1    |
| 4       | 1    |
| 5       | 1    |
+---------+------+
<strong>输出:</strong> 
+---------+
| seat_id |
+---------+
| 3       |
| 4       |
| 5       |
+---------+</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：自连接

我们可以使用自连接的方式，将相邻的两个座位连接起来，然后筛选出连续空余的座位并去重排序即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT DISTINCT a.seat_id
FROM
    Cinema AS a
    JOIN Cinema AS b ON ABS(a.seat_id - b.seat_id) = 1 AND a.free AND b.free
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：窗口函数

我们也可以使用 `LAG` 和 `LEAD` 函数（或者 `SUM() OVER(ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING)`）来获取相邻的座位信息，然后筛选出连续空余的座位并去重排序即可。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seat_id,
            (free + (LAG(free) OVER (ORDER BY seat_id))) AS a,
            (free + (LEAD(free) OVER (ORDER BY seat_id))) AS b
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE a = 2 OR b = 2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            SUM(free = 1) OVER (
                ORDER BY seat_id
                ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING
            ) AS cnt
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE free = 1 AND cnt > 1
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
