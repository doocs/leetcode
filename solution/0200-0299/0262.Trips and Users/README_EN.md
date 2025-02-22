---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0262.Trips%20and%20Users/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [262. Trips and Users](https://leetcode.com/problems/trips-and-users)

[中文文档](/solution/0200-0299/0262.Trips%20and%20Users/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Trips</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| client_id   | int      |
| driver_id   | int      |
| city_id     | int      |
| status      | enum     |
| request_at  | varchar  |     
+-------------+----------+
id is the primary key (column with unique values) for this table.
The table holds all taxi trips. Each trip has a unique id, while client_id and driver_id are foreign keys to the users_id at the Users table.
Status is an ENUM (category) type of (&#39;completed&#39;, &#39;cancelled_by_driver&#39;, &#39;cancelled_by_client&#39;).
</pre>

<p>&nbsp;</p>

<p>Table: <code>Users</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| users_id    | int      |
| banned      | enum     |
| role        | enum     |
+-------------+----------+
users_id is the primary key (column with unique values) for this table.
The table holds all users. Each user has a unique users_id, and role is an ENUM type of (&#39;client&#39;, &#39;driver&#39;, &#39;partner&#39;).
banned is an ENUM (category) type of (&#39;Yes&#39;, &#39;No&#39;).
</pre>

<p>&nbsp;</p>

<p>The <strong>cancellation rate</strong> is computed by dividing the number of canceled (by client or driver) requests with unbanned users by the total number of requests with unbanned users on that day.</p>

<p>Write a solution to find the <strong>cancellation rate</strong> of requests with unbanned users (<strong>both client and driver must not be banned</strong>) each day between <code>&quot;2013-10-01&quot;</code> and <code>&quot;2013-10-03&quot;</code> with <strong>at least</strong> one trip. Round <code>Cancellation Rate</code> to <strong>two decimal</strong> points.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Trips table:
+----+-----------+-----------+---------+---------------------+------------+
| id | client_id | driver_id | city_id | status              | request_at |
+----+-----------+-----------+---------+---------------------+------------+
| 1  | 1         | 10        | 1       | completed           | 2013-10-01 |
| 2  | 2         | 11        | 1       | cancelled_by_driver | 2013-10-01 |
| 3  | 3         | 12        | 6       | completed           | 2013-10-01 |
| 4  | 4         | 13        | 6       | cancelled_by_client | 2013-10-01 |
| 5  | 1         | 10        | 1       | completed           | 2013-10-02 |
| 6  | 2         | 11        | 6       | completed           | 2013-10-02 |
| 7  | 3         | 12        | 6       | completed           | 2013-10-02 |
| 8  | 2         | 12        | 12      | completed           | 2013-10-03 |
| 9  | 3         | 10        | 12      | completed           | 2013-10-03 |
| 10 | 4         | 13        | 12      | cancelled_by_driver | 2013-10-03 |
+----+-----------+-----------+---------+---------------------+------------+
Users table:
+----------+--------+--------+
| users_id | banned | role   |
+----------+--------+--------+
| 1        | No     | client |
| 2        | Yes    | client |
| 3        | No     | client |
| 4        | No     | client |
| 10       | No     | driver |
| 11       | No     | driver |
| 12       | No     | driver |
| 13       | No     | driver |
+----------+--------+--------+
<strong>Output:</strong> 
+------------+-------------------+
| Day        | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 | 0.33              |
| 2013-10-02 | 0.00              |
| 2013-10-03 | 0.50              |
+------------+-------------------+
<strong>Explanation:</strong> 
On 2013-10-01:
  - There were 4 requests in total, 2 of which were canceled.
  - However, the request with Id=2 was made by a banned client (User_Id=2), so it is ignored in the calculation.
  - Hence there are 3 unbanned requests in total, 1 of which was canceled.
  - The Cancellation Rate is (1 / 3) = 0.33
On 2013-10-02:
  - There were 3 requests in total, 0 of which were canceled.
  - The request with Id=6 was made by a banned client, so it is ignored.
  - Hence there are 2 unbanned requests in total, 0 of which were canceled.
  - The Cancellation Rate is (0 / 2) = 0.00
On 2013-10-03:
  - There were 3 requests in total, 1 of which was canceled.
  - The request with Id=8 was made by a banned client, so it is ignored.
  - Hence there are 2 unbanned request in total, 1 of which were canceled.
  - The Cancellation Rate is (1 / 2) = 0.50
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
import pandas as pd


def trips_and_users(trips: pd.DataFrame, users: pd.DataFrame) -> pd.DataFrame:
    # 1) temporal filtering
    trips = trips[trips["request_at"].between("2013-10-01", "2013-10-03")].rename(
        columns={"request_at": "Day"}
    )

    # 2) filtering based not banned
    # 2.1) mappning the column 'banned' to `client_id` and `driver_id`
    df_client = (
        pd.merge(trips, users, left_on="client_id", right_on="users_id", how="left")
        .drop(["users_id", "role"], axis=1)
        .rename(columns={"banned": "banned_client"})
    )
    df_driver = (
        pd.merge(trips, users, left_on="driver_id", right_on="users_id", how="left")
        .drop(["users_id", "role"], axis=1)
        .rename(columns={"banned": "banned_driver"})
    )
    df = pd.merge(
        df_client,
        df_driver,
        left_on=["id", "driver_id", "client_id", "city_id", "status", "Day"],
        right_on=["id", "driver_id", "client_id", "city_id", "status", "Day"],
        how="left",
    )
    # 2.2) filtering based on not banned
    df = df[(df["banned_client"] == "No") & (df["banned_driver"] == "No")]

    # 3) counting the cancelled and total trips per day
    df["status_cancelled"] = df["status"].str.contains("cancelled")
    df = df[["Day", "status_cancelled"]]
    df = df.groupby("Day").agg(
        {"status_cancelled": [("total_cancelled", "sum"), ("total", "count")]}
    )
    df.columns = df.columns.droplevel()
    df = df.reset_index()

    # 4) calculating the ratio
    df["Cancellation Rate"] = (df["total_cancelled"] / df["total"]).round(2)
    return df[["Day", "Cancellation Rate"]]
```

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    request_at AS Day,
    ROUND(AVG(status != 'completed'), 2) AS 'Cancellation Rate'
FROM
    Trips AS t
    JOIN Users AS u1 ON (t.client_id = u1.users_id AND u1.banned = 'No')
    JOIN Users AS u2 ON (t.driver_id = u2.users_id AND u2.banned = 'No')
WHERE request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
