# [603. 连续空余座位](https://leetcode.cn/problems/consecutive-available-seats)

[English Version](/solution/0600-0699/0603.Consecutive%20Available%20Seats/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表:&nbsp;<code>Cinema</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| seat_id     | int  |
| free        | bool |
+-------------+------+
Seat_id是该表的自动递增主键列。
该表的每一行表示第i个座位是否空闲。1表示空闲，0表示被占用。</pre>

<p>&nbsp;</p>

<p>编写一个SQL查询来报告电影院所有连续可用的座位。</p>

<p>返回按 <code>seat_id</code> <strong>升序排序&nbsp;</strong>的结果表。</p>

<p>测试用例的生成使得两个以上的座位连续可用。</p>

<p>查询结果格式如下所示。</p>

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT DISTINCT a.seat_id
FROM
    Cinema AS a
    JOIN Cinema AS b ON abs(a.seat_id - b.seat_id) = 1 AND a.free AND b.free
ORDER BY 1;
```

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            seat_id,
            (free + (lag(free) OVER (ORDER BY seat_id))) AS a,
            (free + (lead(free) OVER (ORDER BY seat_id))) AS b
        FROM Cinema
    )
SELECT seat_id
FROM T
WHERE a = 2 OR b = 2;
```

<!-- tabs:end -->
