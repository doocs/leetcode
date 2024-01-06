# [262. 行程和用户](https://leetcode.cn/problems/trips-and-users)

[English Version](/solution/0200-0299/0262.Trips%20and%20Users/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

表：<code>Trips</code>

<div class="original__bRMd">
<div>
<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| client_id   | int      |
| driver_id   | int      |
| city_id     | int      |
| status      | enum     |
| request_at  | date     |     
+-------------+----------+
id 是这张表的主键（具有唯一值的列）。
这张表中存所有出租车的行程信息。每段行程有唯一 id ，其中 client_id 和 driver_id 是 Users 表中 users_id 的外键。
status 是一个表示行程状态的枚举类型，枚举成员为(‘completed’, ‘cancelled_by_driver’, ‘cancelled_by_client’) 。
</pre>

<p>&nbsp;</p>

<div class="original__bRMd">
<div>
<p>表：<code>Users</code></p>
</div>
</div>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| users_id    | int      |
| banned      | enum     |
| role        | enum     |
+-------------+----------+
users_id 是这张表的主键（具有唯一值的列）。
这张表中存所有用户，每个用户都有一个唯一的 users_id ，role 是一个表示用户身份的枚举类型，枚举成员为 (‘client’, ‘driver’, ‘partner’) 。
banned 是一个表示用户是否被禁止的枚举类型，枚举成员为 (‘Yes’, ‘No’) 。
</pre>

<p>&nbsp;</p>

<p><strong>取消率</strong> 的计算方式如下：(被司机或乘客取消的非禁止用户生成的订单数量) / (非禁止用户生成的订单总数)。</p>

<p>编写解决方案找出&nbsp;<code>"2013-10-01"</code><strong>&nbsp;</strong>至&nbsp;<code>"2013-10-03"</code><strong>&nbsp;</strong>期间非禁止用户（<strong>乘客和司机都必须未被禁止</strong>）的取消率。非禁止用户即 banned 为 No 的用户，禁止用户即 banned 为 Yes 的用户。其中取消率 <code>Cancellation Rate</code> 需要四舍五入保留 <strong>两位小数</strong> 。</p>

<p>返回结果表中的数据 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
Trips 表：
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
Users 表：
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
<strong>输出：</strong>
+------------+-------------------+
| Day        | Cancellation Rate |
+------------+-------------------+
| 2013-10-01 | 0.33              |
| 2013-10-02 | 0.00              |
| 2013-10-03 | 0.50              |
+------------+-------------------+
<strong>解释：</strong>
2013-10-01：
  - 共有 4 条请求，其中 2 条取消。
  - 然而，id=2 的请求是由禁止用户（user_id=2）发出的，所以计算时应当忽略它。
  - 因此，总共有 3 条非禁止请求参与计算，其中 1 条取消。
  - 取消率为 (1 / 3) = 0.33
2013-10-02：
  - 共有 3 条请求，其中 0 条取消。
  - 然而，id=6 的请求是由禁止用户发出的，所以计算时应当忽略它。
  - 因此，总共有 2 条非禁止请求参与计算，其中 0 条取消。
  - 取消率为 (0 / 2) = 0.00
2013-10-03：
  - 共有 3 条请求，其中 1 条取消。
  - 然而，id=8 的请求是由禁止用户发出的，所以计算时应当忽略它。
  - 因此，总共有 2 条非禁止请求参与计算，其中 1 条取消。
  - 取消率为 (1 / 2) = 0.50
</pre>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

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

### **Pandas**

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

<!-- tabs:end -->
