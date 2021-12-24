# [1435. Create a Session Bar Chart](https://leetcode.com/problems/create-a-session-bar-chart)

[中文文档](/solution/1400-1499/1435.Create%20a%20Session%20Bar%20Chart/README.md)

## Description

<p>Table: <code>Sessions</code></p>

<pre>
+---------------------+---------+
| Column Name         | Type    |
+---------------------+---------+
| session_id          | int     |
| duration            | int     |
+---------------------+---------+
session_id is the primary key for this table.
duration is the time in seconds that a user has visited the application.
</pre>

<p>&nbsp;</p>

<p>You want to know how long a user visits your application. You decided to create bins of &quot;[0-5&gt;&quot;, &quot;[5-10&gt;&quot;, &quot;[10-15&gt;&quot; and &quot;15 minutes or more&quot; and count the number of sessions on it.</p>

<p>Write an SQL query to report the (bin, total) in <strong>any</strong> order.</p>

<p>The query result format is in the following example.</p>

<pre>
Sessions table:
+-------------+---------------+
| session_id  | duration      |
+-------------+---------------+
| 1           | 30            |
| 2           | 199           |
| 3           | 299           |
| 4           | 580           |
| 5           | 1000          |
+-------------+---------------+

Result table:
+--------------+--------------+
| bin          | total        |
+--------------+--------------+
| [0-5&gt;        | 3            |
| [5-10&gt;       | 1            |
| [10-15&gt;      | 0            |
| 15 or more   | 1            |
+--------------+--------------+

For session_id 1, 2 and 3 have a duration greater or equal than 0 minutes and less than 5 minutes.
For session_id 4 has a duration greater or equal than 5 minutes and less than 10 minutes.
There are no session with a duration greater or equial than 10 minutes and less than 15 minutes.
For session_id 5 has a duration greater or equal than 15 minutes.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
