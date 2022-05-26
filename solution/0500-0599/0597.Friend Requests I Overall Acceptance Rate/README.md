# [597. 好友申请 I：总体通过率](https://leetcode.cn/problems/friend-requests-i-overall-acceptance-rate)

[English Version](/solution/0500-0599/0597.Friend%20Requests%20I%20Overall%20Acceptance%20Rate/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>FriendRequest</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| sender_id      | int     |
| send_to_id     | int     |
| request_date   | date    |
+----------------+---------+
此表没有主键，它可能包含重复项。
该表包含发送请求的用户的 ID ，接受请求的用户的 ID 以及请求的日期。
</pre>

<p>&nbsp;</p>

<p>表：<code>RequestAccepted</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |
+----------------+---------+
此表没有主键，它可能包含重复项。
该表包含发送请求的用户的 ID ，接受请求的用户的 ID 以及请求通过的日期。</pre>

<p>&nbsp;</p>

<p>写一个查询语句，求出好友申请的通过率，用 2 位小数表示。通过率由接受好友申请的数目除以申请总数。</p>

<p><strong>提示：</strong></p>

<ul>
	<li>通过的好友申请不一定都在表&nbsp;<code>friend_request</code>&nbsp;中。你只需要统计总的被通过的申请数（不管它们在不在表&nbsp;<code>FriendRequest</code>&nbsp;中），并将它除以申请总数，得到通过率</li>
	<li>一个好友申请发送者有可能会给接受者发几条好友申请，也有可能一个好友申请会被通过好几次。这种情况下，重复的好友申请只统计一次。</li>
	<li>如果一个好友申请都没有，你应该返回&nbsp;<code>accept_rate</code>&nbsp;为 0.00 。</li>
</ul>

<p>查询结果应该如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
FriendRequest 表：
+-----------+------------+--------------+
| sender_id | send_to_id | request_date |
+-----------+------------+--------------+
| 1         | 2          | 2016/06/01   |
| 1         | 3          | 2016/06/01   |
| 1         | 4          | 2016/06/01   |
| 2         | 3          | 2016/06/02   |
| 3         | 4          | 2016/06/09   |
+-----------+------------+--------------+
RequestAccepted 表：
+--------------+-------------+-------------+
| requester_id | accepter_id | accept_date |
+--------------+-------------+-------------+
| 1            | 2           | 2016/06/03  |
| 1            | 3           | 2016/06/08  |
| 2            | 3           | 2016/06/08  |
| 3            | 4           | 2016/06/09  |
| 3            | 4           | 2016/06/10  |
+--------------+-------------+-------------+
<strong>输出：</strong>
+-------------+
| accept_rate |
+-------------+
| 0.8         |
+-------------+
<strong>解释：</strong>
总共有 5 个请求，有 4 个不同的通过请求，所以通过率是 0.80</pre>

<p>&nbsp;</p>

<p><strong>进阶:</strong></p>

<ul>
	<li>你能写一个查询语句得到每个月的通过率吗？</li>
	<li>你能求出每一天的累计通过率吗？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql
SELECT IFNULL(ROUND((
		SELECT COUNT(DISTINCT requester_id, accepter_id)
		FROM RequestAccepted
	) / (
		SELECT COUNT(DISTINCT sender_id, send_to_id)
		FROM FriendRequest
	), 2), 0.00) AS accept_rate;
```

<!-- tabs:end -->
