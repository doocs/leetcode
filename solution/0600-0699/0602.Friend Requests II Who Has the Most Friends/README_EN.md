# [602. Friend Requests II Who Has the Most Friends](https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends)

[中文文档](/solution/0600-0699/0602.Friend%20Requests%20II%20Who%20Has%20the%20Most%20Friends/README.md)

## Description

<p>In social network like Facebook or Twitter, people send friend requests and accept others&#39; requests as well.</p>

<p>&nbsp;</p>

<p>Table <code>request_accepted</code></p>

<pre>
+--------------+-------------+------------+
| requester_id | accepter_id | accept_date|
|--------------|-------------|------------|
| 1            | 2           | 2016_06-03 |
| 1            | 3           | 2016-06-08 |
| 2            | 3           | 2016-06-08 |
| 3            | 4           | 2016-06-09 |
+--------------+-------------+------------+
This table holds the data of friend acceptance, while <b>requester_id</b> and <b>accepter_id</b> both are the id of a person.
</pre>

<p>&nbsp;</p>

<p>Write a query to find the the people who has most friends and the most friends number under the following rules:</p>

<ul>
	<li>It is guaranteed there is only 1 people having the most friends.</li>
	<li>The friend request could only been accepted once, which mean there is no multiple records with the same <b>requester_id</b> and <b>accepter_id</b> value.</li>
</ul>

<p>For the sample data above, the result is:</p>

<pre>
Result table:
+------+------+
| id   | num  |
|------|------|
| 3    | 3    |
+------+------+
The person with id &#39;3&#39; is a friend of people &#39;1&#39;, &#39;2&#39; and &#39;4&#39;, so he has 3 friends in total, which is the most number than any others.
</pre>

<p><b>Follow-up:</b><br />
In the real world, multiple people could have the same most number of friends, can you find all these people in this case?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
