# [262. 行程和用户](https://leetcode-cn.com/problems/trips-and-users)

## 题目描述
<!-- 这里写题目描述 -->
<p><code>Trips</code>&nbsp;表中存所有出租车的行程信息。每段行程有唯一键 Id，Client_Id 和&nbsp;Driver_Id 是&nbsp;<code>Users</code>&nbsp;表中 Users_Id 的外键。Status 是枚举类型，枚举成员为 (&lsquo;completed&rsquo;, &lsquo;cancelled_by_driver&rsquo;, &lsquo;cancelled_by_client&rsquo;)。</p>

<pre>+----+-----------+-----------+---------+--------------------+----------+
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

<p><code>Users</code>&nbsp;表存所有用户。每个用户有唯一键 Users_Id。Banned 表示这个用户是否被禁止，Role 则是一个表示（&lsquo;client&rsquo;, &lsquo;driver&rsquo;, &lsquo;partner&rsquo;）的枚举类型。</p>

<pre>+----------+--------+--------+
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

<p>写一段 SQL 语句查出&nbsp;<strong>2013年10月1日&nbsp;</strong>至&nbsp;<strong>2013年10月3日&nbsp;</strong>期间非禁止用户的取消率。基于上表，你的 SQL 语句应返回如下结果，取消率（Cancellation Rate）保留两位小数。</p>

<p>取消率的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)</p>

<pre>+------------+-------------------+
|     Day    | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 |       0.33        |
| 2013-10-02 |       0.00        |
| 2013-10-03 |       0.50        |
+------------+-------------------+
</pre>

<p><strong>致谢:</strong><br>
非常感谢&nbsp;<a href="https://leetcode.com/discuss/user/cak1erlizhou">@cak1erlizhou</a>&nbsp;详细的提供了这道题和相应的测试用例。</p>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
