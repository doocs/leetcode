# [597. Friend Requests I Overall Acceptance Rate](https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate)

[中文文档](/solution/0500-0599/0597.Friend%20Requests%20I%20Overall%20Acceptance%20Rate/README.md)

## Description

<p>Table: <code>FriendRequest</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| sender_id      | int     |
| send_to_id     | int     |
| request_date   | date    |
+----------------+---------+
There is no primary key for this table, it may contain duplicates.
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date of the request.
</pre>

<p>&nbsp;</p>

<p>Table: <code>RequestAccepted</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| requester_id   | int     |
| accepter_id    | int     |
| accept_date    | date    |
+----------------+---------+
There is no primary key for this table, it may contain duplicates.
This table contains the ID of the user who sent the request, the ID of the user who received the request, and the date when the request was accepted.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the overall acceptance rate of requests, which is the number of acceptance divided by the number of requests. Return the answer rounded to 2 decimals places.</p>

<p><strong>Note that:</strong></p>

<ul>
	<li>The accepted requests are not necessarily from the table <code>friend_request</code>. In this case, Count the total accepted requests (no matter whether they are in the original requests), and divide it by the number of requests to get the acceptance rate.</li>
	<li>It is possible that a sender sends multiple requests to the same receiver, and a request could be accepted more than once. In this case, the &lsquo;duplicated&rsquo; requests or acceptances are only counted once.</li>
	<li>If there are no requests at all, you should return 0.00 as the <code>accept_rate</code>.</li>
</ul>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
FriendRequest table:
+-----------+------------+--------------+
| sender_id | send_to_id | request_date |
+-----------+------------+--------------+
| 1         | 2          | 2016/06/01   |
| 1         | 3          | 2016/06/01   |
| 1         | 4          | 2016/06/01   |
| 2         | 3          | 2016/06/02   |
| 3         | 4          | 2016/06/09   |
+-----------+------------+--------------+
RequestAccepted table:
+--------------+-------------+-------------+
| requester_id | accepter_id | accept_date |
+--------------+-------------+-------------+
| 1            | 2           | 2016/06/03  |
| 1            | 3           | 2016/06/08  |
| 2            | 3           | 2016/06/08  |
| 3            | 4           | 2016/06/09  |
| 3            | 4           | 2016/06/10  |
+--------------+-------------+-------------+
<strong>Output:</strong> 
+-------------+
| accept_rate |
+-------------+
| 0.8         |
+-------------+
<strong>Explanation:</strong> 
There are 4 unique accepted requests, and there are 5 requests in total. So the rate is 0.80.
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Could you write a query to return the acceptance rate for every month?</li>
	<li>Could you write a query to return the cumulative acceptance rate for every day?</li>
</ul>

## Solutions

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
