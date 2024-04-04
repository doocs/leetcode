# [3089. 查找突发行为](https://leetcode.cn/problems/find-bursty-behavior)

[English Version](/solution/3000-3099/3089.Find%20Bursty%20Behavior/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Posts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| post_id     | int     |
| user_id     | int     |
| post_date   | date    |
+-------------+---------+
post_id 是这张表的主键（有不同值的列）。
这张表的每一行包含 post_id，user_id 和 post_date。
</pre>

<p>编写一个解决方案来找到在 <code>2024</code> 年 2 月期间在发布模式中表现出 <strong>突发行为</strong> 的用户。<strong>突发行为</strong>&nbsp;指用户在&nbsp;<code>2024</code> 年 2 月 <strong>任何</strong> <strong>连续 7 天</strong> 的时段中发帖频率是其 <strong>平均</strong> 每周发帖频率的 <strong>至少两倍</strong>。</p>

<p><strong>注意：</strong>&nbsp;在你的统计中只包含 2 月 <code>1</code> 日 到 2 月 <code>28</code> 日，也就是说你应该把 2 月记为正好 <code>4</code> 周。</p>

<p>返回结果表，以<em>&nbsp;</em><code>user_id</code><em> </em><strong>升序</strong><em>&nbsp;</em>排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Posts 表：</p>

<pre class="example-io">
+---------+---------+------------+
| post_id | user_id | post_date  |
+---------+---------+------------+
| 1       | 1       | 2024-02-27 |
| 2       | 5       | 2024-02-06 |
| 3       | 3       | 2024-02-25 |
| 4       | 3       | 2024-02-14 |
| 5       | 3       | 2024-02-06 |
| 6       | 2       | 2024-02-25 |
+---------+---------+------------+
</pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+---------+----------------+------------------+
| user_id | max_7day_posts | avg_weekly_posts |
+---------+----------------+------------------+
| 1       | 1              | 0.2500           |
| 2       | 1              | 0.2500           |
| 5       | 1              | 0.2500           |
+---------+----------------+------------------+
</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>用户 1：</strong>2 月份只发布了 1 个帖子，平均每周发布 0.25 个帖子，任何 7 天期间最多发布 1 个帖子。</li>
	<li><strong>用户 2：</strong>也只发了 1 个帖子，与用户 1 相同的平均和最大 7 天发帖频率。</li>
	<li><strong>用户 5：</strong>与用户 1 和用户 2 一样，用户 5 在整个 2 月份只发布了 1 篇文章，得到相同的平均和最大 7 天发帖频率。</li>
	<li><strong>用户 3：</strong>虽然用户 3 发布的帖子比其他用户多（3 篇），但他在连续 7 天期间中没有达到每周平均发帖频率的两倍，因此没有在输出中列出。</li>
</ul>

<p><b>注意：</b>&nbsp;输出表以 user_id 升序排序。</p>
</div>

## 解法

### 方法一：自连接 + 分组统计

我们可以使用自连接，将表 `Posts` 与自身连接，连接条件是 `p1.user_id = p2.user_id` 且 `p2.post_date` 在 `p1.post_date` 和 `p1.post_date` 后 `6` 天之间，然后我们将连接结果按照 `p1.user_id` 和 `p1.post_id` 分组，即可统计出每个用户在每天的 7 天内的发帖数量，我们将这个结果保存在表 `P` 中。

接着我们统计出每个用户在 2024 年 2 月份的每周平均发帖数量，保存在表 `T` 中。注意，我们需要查找 `post_date` 在 `2024-02-01` 和 `2024-02-28` 之间的记录，将记录按照 `user_id` 分组，然后统计每个用户的发帖数量，最后除以 `4` 即可得到每周平均发帖数量，我们将这个结果保存在表 `T` 中。

最后，我们将表 `P` 和表 `T` 连接，连接条件是 `P.user_id = T.user_id`，然后按照 `user_id` 分组，统计出每个用户在 7 天内的最大发帖数量，最后筛选出满足条件 `max_7day_posts >= avg_weekly_posts * 2` 的记录，即可得到结果。注意，我们需要按照 `user_id` 升序排序。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    P AS (
        SELECT p1.user_id AS user_id, COUNT(1) AS cnt
        FROM
            Posts AS p1
            JOIN Posts AS p2
                ON p1.user_id = p2.user_id
                AND p2.post_date BETWEEN p1.post_date AND DATE_ADD(p1.post_date, INTERVAL 6 DAY)
        GROUP BY p1.user_id, p1.post_id
    ),
    T AS (
        SELECT user_id, COUNT(1) / 4 AS avg_weekly_posts
        FROM Posts
        WHERE post_date BETWEEN '2024-02-01' AND '2024-02-28'
        GROUP BY 1
    )
SELECT user_id, MAX(cnt) AS max_7day_posts, avg_weekly_posts
FROM
    P
    JOIN T USING (user_id)
GROUP BY 1
HAVING max_7day_posts >= avg_weekly_posts * 2
ORDER BY 1;
```

```python
import pandas as pd


def find_bursty_behavior(posts: pd.DataFrame) -> pd.DataFrame:
    # 子查询 P
    p1 = pd.merge(
        posts, posts, on="user_id", suffixes=("_1", "_2")
    )  # 合并帖子表自身，根据用户ID
    p1 = p1[
        p1["post_date_2"].between(
            p1["post_date_1"], p1["post_date_1"] + pd.Timedelta(days=6)
        )
    ]  # 筛选出相邻 7 天内的帖子
    p1 = (
        p1.groupby(["user_id", "post_id_1"]).size().reset_index(name="cnt")
    )  # 统计每个用户在相邻 7 天内的帖子数

    # 子查询 T
    t = posts[
        (posts["post_date"] >= "2024-02-01") & (posts["post_date"] <= "2024-02-28")
    ]  # 筛选出 2024 年 2 月份的帖子
    t = (
        t.groupby("user_id").size().div(4).reset_index(name="avg_weekly_posts")
    )  # 计算每个用户平均每周的帖子数

    # 连接 P 和 T
    merged_df = pd.merge(p1, t, on="user_id", how="inner")  # 内连接 P 和 T

    # 过滤
    filtered_df = merged_df[
        merged_df["cnt"] >= merged_df["avg_weekly_posts"] * 2
    ]  # 过滤出满足条件的行

    # 聚合
    result_df = (
        filtered_df.groupby("user_id")
        .agg({"cnt": "max", "avg_weekly_posts": "first"})
        .reset_index()
    )  # 对满足条件的行按用户ID聚合
    result_df.columns = ["user_id", "max_7day_posts", "avg_weekly_posts"]  # 重命名列名

    # 排序
    result_df.sort_values(by="user_id", inplace=True)  # 按用户ID排序

    return result_df
```

<!-- tabs:end -->

<!-- end -->
