# [1141. 查询近 30 天活跃用户数](https://leetcode-cn.com/problems/user-activity-for-the-past-30-days-i)

[English Version](/solution/1100-1199/1141.User%20Activity%20for%20the%20Past%2030%20Days%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>活动记录表：<code>Activity</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| session_id    | int     |
| activity_date | date    |
| activity_type | enum    |
+---------------+---------+
该表是用户在社交网站的活动记录。
该表没有主键，可能包含重复数据。
activity_type 字段为以下四种值 (&#39;open_session&#39;, &#39;end_session&#39;, &#39;scroll_down&#39;, &#39;send_message&#39;)。
每个 session_id 只属于一个用户。
</pre>

<p>&nbsp;</p>

<p>请写SQL查询出截至&nbsp;<strong>2019-07-27</strong>（包含2019-07-27）<strong>，近&nbsp;</strong>30天的每日活跃用户数（当天只要有一条活动记录，即为活跃用户）。</p>

<p>查询结果示例如下：</p>

<pre>Activity table:
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
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+

Result table:
+------------+--------------+ 
| day        | active_users |
+------------+--------------+ 
| 2019-07-20 | 2            |
| 2019-07-21 | 2            |
+------------+--------------+ 
非活跃用户的记录不需要展示。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
