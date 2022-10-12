# [2020. Number of Accounts That Did Not Stream](https://leetcode.com/problems/number-of-accounts-that-did-not-stream)

[中文文档](/solution/2000-2099/2020.Number%20of%20Accounts%20That%20Did%20Not%20Stream/README.md)

## Description

<p>Table: <code>Subscriptions</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| account_id  | int  |
| start_date  | date |
| end_date    | date |
+-------------+------+
account_id is the primary key column for this table.
Each row of this table indicates the start and end dates of an account&#39;s subscription.
Note that always start_date &lt; end_date.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Streams</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| session_id  | int  |
| account_id  | int  |
| stream_date | date |
+-------------+------+
session_id is the primary key column for this table.
account_id is a foreign key from the Subscriptions table.
Each row of this table contains information about the account and the date associated with a stream session.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the number of accounts that bought a subscription in <code>2021</code> but did not have any stream session.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Subscriptions table:
+------------+------------+------------+
| account_id | start_date | end_date   |
+------------+------------+------------+
| 9          | 2020-02-18 | 2021-10-30 |
| 3          | 2021-09-21 | 2021-11-13 |
| 11         | 2020-02-28 | 2020-08-18 |
| 13         | 2021-04-20 | 2021-09-22 |
| 4          | 2020-10-26 | 2021-05-08 |
| 5          | 2020-09-11 | 2021-01-17 |
+------------+------------+------------+
Streams table:
+------------+------------+-------------+
| session_id | account_id | stream_date |
+------------+------------+-------------+
| 14         | 9          | 2020-05-16  |
| 16         | 3          | 2021-10-27  |
| 18         | 11         | 2020-04-29  |
| 17         | 13         | 2021-08-08  |
| 19         | 4          | 2020-12-31  |
| 13         | 5          | 2021-01-05  |
+------------+------------+-------------+
<strong>Output:</strong> 
+----------------+
| accounts_count |
+----------------+
| 2              |
+----------------+
<strong>Explanation:</strong> Users 4 and 9 did not stream in 2021.
User 11 did not subscribe in 2021.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
