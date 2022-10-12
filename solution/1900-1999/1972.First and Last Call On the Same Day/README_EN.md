# [1972. First and Last Call On the Same Day](https://leetcode.com/problems/first-and-last-call-on-the-same-day)

[中文文档](/solution/1900-1999/1972.First%20and%20Last%20Call%20On%20the%20Same%20Day/README.md)

## Description

<p>Table: <code>Calls</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| caller_id    | int      |
| recipient_id | int      |
| call_time    | datetime |
+--------------+----------+
(caller_id, recipient_id, call_time) is the primary key for this table.
Each row contains information about the time of a phone call between caller_id and recipient_id.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of the users whose first and last calls on <strong>any day</strong> were with <strong>the same person</strong>. Calls are counted regardless of being the caller or the recipient.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Calls table:
+-----------+--------------+---------------------+
| caller_id | recipient_id | call_time           |
+-----------+--------------+---------------------+
| 8         | 4            | 2021-08-24 17:46:07 |
| 4         | 8            | 2021-08-24 19:57:13 |
| 5         | 1            | 2021-08-11 05:28:44 |
| 8         | 3            | 2021-08-17 04:04:15 |
| 11        | 3            | 2021-08-17 13:07:00 |
| 8         | 11           | 2021-08-17 22:22:22 |
+-----------+--------------+---------------------+
<strong>Output:</strong> 
+---------+
| user_id |
+---------+
| 1       |
| 4       |
| 5       |
| 8       |
+---------+
<strong>Explanation:</strong> 
On 2021-08-24, the first and last call of this day for user 8 was with user 4. User 8 should be included in the answer.
Similarly, user 4 on 2021-08-24 had their first and last call with user 8. User 4 should be included in the answer.
On 2021-08-11, user 1 and 5 had a call. This call was the only call for both of them on this day. Since this call is the first and last call of the day for both of them, they should both be included in the answer.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
