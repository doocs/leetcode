# [1142. 过去 30 天的用户活动 II](https://leetcode-cn.com/problems/user-activity-for-the-past-30-days-ii)

[English Version](/solution/1100-1199/1142.User%20Activity%20for%20the%20Past%2030%20Days%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Activity</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
该表没有主键，它可能有重复的行。
activity_type 列是 ENUM（“ open_session”，“ end_session”，“ scroll_down”，“ send_message”）中的某一类型。
该表显示了社交媒体网站的用户活动。
请注意，每个会话完全属于一个用户。</pre>

<p> </p>

<p>编写SQL查询以查找截至2019年7月27日（含）的30天内每个用户的平均会话数，四舍五入到小数点后两位。我们只统计那些会话期间用户至少进行一项活动的有效会话。</p>

<p> </p>

<p>查询结果格式如下例所示：</p>

<pre>
Activity table:
+---------+------------+---------------+---------------+
| user_id | session_id | activity_date | activity_type |
+---------+------------+---------------+---------------+
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 3       | 5          | 2019-07-21    | open_session  |
| 3       | 5          | 2019-07-21    | scroll_down   |
| 3       | 5          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+

Result table:
+---------------------------+ 
| average_sessions_per_user |
+---------------------------+ 
| 1.33                      |
+---------------------------+ 
User 1 和 2 在过去30天内各自进行了1次会话，而用户3进行了2次会话，因此平均值为（1 +1 + 2）/ 3 = 1.33。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
