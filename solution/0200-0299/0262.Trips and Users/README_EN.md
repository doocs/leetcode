# [262. Trips and Users](https://leetcode.com/problems/trips-and-users)

## Description
<p>The <code>Trips</code> table holds all taxi trips. Each trip has a unique Id, while Client_Id and Driver_Id are both foreign keys to the Users_Id at the <code>Users</code> table. Status is an ENUM type of (&lsquo;completed&rsquo;, &lsquo;cancelled_by_driver&rsquo;, &lsquo;cancelled_by_client&rsquo;).</p>

<pre>
+----+-----------+-----------+---------+--------------------+----------+
| Id | Client_Id | Driver_Id | City_Id |        Status      |Request_at|
+----+-----------+-----------+---------+--------------------+----------+
| 1  |     1     |    10     |    1    |     completed      |2013-10-01|
| 2  |     2     |    11     |    1    | cancelled_by_driver|2013-10-01|
| 3  |     3     |    12     |    6    |     completed      |2013-10-01|
| 4  |     4     |    13     |    6    | cancelled_by_client|2013-10-01|
| 5  |     1     |    10     |    1    |     completed      |2013-10-02|
| 6  |     2     |    11     |    6    |     completed      |2013-10-02|
| 7  |     3     |    12     |    6    |     completed      |2013-10-02|
| 8  |     2     |    12     |    12   |     completed      |2013-10-03|
| 9  |     3     |    10     |    12   |     completed      |2013-10-03| 
| 10 |     4     |    13     |    12   | cancelled_by_driver|2013-10-03|
+----+-----------+-----------+---------+--------------------+----------+
</pre>

<p>The <code>Users</code> table holds all users. Each user has an unique Users_Id, and Role is an ENUM type of (&lsquo;client&rsquo;, &lsquo;driver&rsquo;, &lsquo;partner&rsquo;).</p>

<pre>
+----------+--------+--------+
| Users_Id | Banned |  Role  |
+----------+--------+--------+
|    1     |   No   | client |
|    2     |   Yes  | client |
|    3     |   No   | client |
|    4     |   No   | client |
|    10    |   No   | driver |
|    11    |   No   | driver |
|    12    |   No   | driver |
|    13    |   No   | driver |
+----------+--------+--------+
</pre>

<p>Write a SQL query to find the cancellation rate of requests made by unbanned users (both client and driver must be unbanned)&nbsp;between <strong>Oct 1, 2013</strong> and <strong>Oct 3, 2013</strong>. The cancellation rate is computed by dividing the number of canceled (by client or driver) requests&nbsp;made by unbanned users by the total number of requests&nbsp;made by unbanned users.</p>

<p>For the above tables, your SQL query should return the following rows with the cancellation rate being rounded to <em>two</em> decimal places.</p>

<pre>
+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+
</pre>

<p><strong>Credits:</strong><br />
Special thanks to <a href="https://leetcode.com/discuss/user/cak1erlizhou">@cak1erlizhou</a> for contributing this question, writing the problem description and adding part of the test cases.</p>



## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
