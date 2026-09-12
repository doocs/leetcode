---
comments: true
difficulty: 中等
tags:
    - 数据库
---

<!-- problem:start -->

# [3124. 查找最长的电话 🔒](https://leetcode.cn/problems/find-longest-calls)

[English Version](/solution/3100-3199/3124.Find%20Longest%20Calls/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Contacts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| first_name  | varchar |
| last_name   | varchar |
+-------------+---------+
id 是这张表的主键（有不同值的列）。
id 是 Calls 表的外键（引用列）。
这张表的每一行都包含 id，first_name 和 last_name。
</pre>

<p>表：<code>Calls</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| contact_id  | int  |
| type        | enum |
| duration    | int  |
+-------------+------+
(contact_id, type, duration) 是这张表的主键（有不同值的列）。
type 字段是 ('incoming', 'outgoing') 的 ENUM (category)。
这张表的每一行包含有 calls, 包括 contact_id，type 和以秒为单位的 duration 的信息。
</pre>

<p>编写一个解决方案来找到&nbsp;<strong>三个最长的呼入</strong>&nbsp;和&nbsp;<strong>呼出</strong>&nbsp;电话。</p>

<p>返回结果表，以&nbsp;<code>type</code>，<code>duration</code>&nbsp;和&nbsp;<code>first_name</code>&nbsp;<em><strong>降序排序</strong>&nbsp;，<code>duration</code>&nbsp;的格式必须为&nbsp;<strong>HH:MM:SS</strong>。</em></p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p>Contacts 表：</p>

<pre class="example-io">
+----+------------+-----------+
| id | first_name | last_name |
+----+------------+-----------+
| 1  | John       | Doe       |
| 2  | Jane       | Smith     |
| 3  | Alice      | Johnson   |
| 4  | Michael    | Brown     |
| 5  | Emily      | Davis     |
+----+------------+-----------+        
</pre>

<p>Calls 表：</p>

<pre class="example-io">
+------------+----------+----------+
| contact_id | type     | duration |
+------------+----------+----------+
| 1          | incoming | 120      |
| 1          | outgoing | 180      |
| 2          | incoming | 300      |
| 2          | outgoing | 240      |
| 3          | incoming | 150      |
| 3          | outgoing | 360      |
| 4          | incoming | 420      |
| 4          | outgoing | 200      |
| 5          | incoming | 180      |
| 5          | outgoing | 280      |
+------------+----------+----------+
        </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+----------+-------------------+
| first_name| type     | duration_formatted|
+-----------+----------+-------------------+
| Michael   | incoming | 00:07:00          |
| Jane      | incoming | 00:05:00          |
| Emily     | incoming | 00:03:00          |
| Alice     | outgoing | 00:06:00          |
| Emily     | outgoing | 00:04:40          |
| Jane      | outgoing | 00:04:00          |
+-----------+----------+-------------------+
        </pre>

<p><strong>解释:</strong></p>

<ul>
	<li>Michael 有一通长达 7 分钟的呼入电话。</li>
	<li>Jane 有一通长达 5&nbsp;分钟的呼入电话。</li>
	<li>Emily 有一通长达 3&nbsp;分钟的呼入电话。</li>
	<li>Alice 有一通长达 6&nbsp;分钟的呼出电话。</li>
	<li>Emily 有一通长达 4&nbsp;分 40 秒的呼出电话。</li>
	<li>Jane 有一通长达 4&nbsp;分钟的呼出电话。</li>
</ul>

<p><b>注意：</b>输出表以&nbsp;type，duration&nbsp;和 first_name 降序排序。</p>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：等值连接 + 窗口函数

<!-- thinking:start -->

> **思考**
>
> 需要按通话类型取出时长前三，并格式化时分秒。分组后手写排序可以完成，但并列名次要用密集名次处理。
>
> 联系人与通话先按编号等值连接，再在类型内对 $duration$ 做降序 `rank`，筛选名次不超过 $3$ 的行。
>
> 时长先换成时分秒字符串，最后按类型、格式化时长与姓名排序输出。窗口函数一次完成组内排名。

<!-- thinking:end -->

我们可以使用等值连接将两张表连接起来，然后使用窗口函数 `RANK()` 计算每个类型的电话的排名。最后，我们只需要筛选出排名前三的电话即可。

<!-- tabs:start -->

#### MySQL

```sql
WITH
    T AS (
        SELECT
            first_name,
            type,
            DATE_FORMAT(SEC_TO_TIME(duration), "%H:%i:%s") AS duration_formatted,
            RANK() OVER (
                PARTITION BY type
                ORDER BY duration DESC
            ) AS rk
        FROM
            Calls AS c1
            JOIN Contacts AS c2 ON c1.contact_id = c2.id
    )
SELECT
    first_name,
    type,
    duration_formatted
FROM T
WHERE rk <= 3
ORDER BY 2, 3 DESC, 1 DESC;
```

#### Python3

```python
import pandas as pd


def find_longest_calls(contacts: pd.DataFrame, calls: pd.DataFrame) -> pd.DataFrame:
    merged_data = calls.merge(contacts, left_on="contact_id", right_on="id")
    merged_data["duration_formatted"] = (
        merged_data["duration"] // 3600 * 10000
        + merged_data["duration"] % 3600 // 60 * 100
        + merged_data["duration"] % 60
    ).apply(lambda x: "{:02}:{:02}:{:02}".format(x // 10000, x // 100 % 100, x % 100))

    merged_data["rk"] = merged_data.groupby("type")["duration"].rank(
        method="dense", ascending=False
    )

    result = merged_data[merged_data["rk"] <= 3][
        ["first_name", "type", "duration_formatted"]
    ]
    result = result.sort_values(
        by=["type", "duration_formatted", "first_name"], ascending=[True, False, False]
    )
    return result
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
