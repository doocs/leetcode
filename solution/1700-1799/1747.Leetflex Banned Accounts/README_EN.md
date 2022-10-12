# [1747. Leetflex Banned Accounts](https://leetcode.com/problems/leetflex-banned-accounts)

[中文文档](/solution/1700-1799/1747.Leetflex%20Banned%20Accounts/README.md)

## Description

<p>Table: <code>LogInfo</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| account_id  | int      |
| ip_address  | int      |
| login       | datetime |
| logout      | datetime |
+-------------+----------+
There is no primary key for this table, and it may contain duplicates.
The table contains information about the login and logout dates of Leetflex accounts. It also contains the IP address from which the account was logged in and out.
It is guaranteed that the logout time is after the login time.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the <code>account_id</code> of the accounts that should be banned from Leetflex. An account should be banned if it was logged in at some moment from two different IP addresses.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
LogInfo table:
+------------+------------+---------------------+---------------------+
| account_id | ip_address | login               | logout              |
+------------+------------+---------------------+---------------------+
| 1          | 1          | 2021-02-01 09:00:00 | 2021-02-01 09:30:00 |
| 1          | 2          | 2021-02-01 08:00:00 | 2021-02-01 11:30:00 |
| 2          | 6          | 2021-02-01 20:30:00 | 2021-02-01 22:00:00 |
| 2          | 7          | 2021-02-02 20:30:00 | 2021-02-02 22:00:00 |
| 3          | 9          | 2021-02-01 16:00:00 | 2021-02-01 16:59:59 |
| 3          | 13         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
| 4          | 10         | 2021-02-01 16:00:00 | 2021-02-01 17:00:00 |
| 4          | 11         | 2021-02-01 17:00:00 | 2021-02-01 17:59:59 |
+------------+------------+---------------------+---------------------+
<strong>Output:</strong> 
+------------+
| account_id |
+------------+
| 1          |
| 4          |
+------------+
<strong>Explanation:</strong> 
Account ID 1 --&gt; The account was active from &quot;2021-02-01 09:00:00&quot; to &quot;2021-02-01 09:30:00&quot; with two different IP addresses (1 and 2). It should be banned.
Account ID 2 --&gt; The account was active from two different addresses (6, 7) but in <strong>two different times</strong>.
Account ID 3 --&gt; The account was active from two different addresses (9, 13) on the same day but <strong>they do not intersect at any moment</strong>.
Account ID 4 --&gt; The account was active from &quot;2021-02-01 17:00:00&quot; to &quot;2021-02-01 17:00:00&quot; with two different IP addresses (10 and 11). It should be banned.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
