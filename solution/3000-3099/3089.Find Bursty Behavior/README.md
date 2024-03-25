# [3089. Find Bursty Behavior](https://leetcode.cn/problems/find-bursty-behavior)

[English Version](/solution/3000-3099/3089.Find%20Bursty%20Behavior/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>Table: <code>Posts</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| post_id     | int     |
| user_id     | int     |
| post_date   | date    |
+-------------+---------+
post_id is the primary key (column with unique values) for this table.
Each row of this table contains post_id, user_id, and post_date.
</pre>

<p>Write a solution to find users who demonstrate <strong>bursty behavior</strong> in their posting patterns during February <code>2024</code>. <strong>Bursty behavior</strong> is defined as <strong>any</strong> period of <strong>7</strong> <strong>consecutive</strong> days where a user&#39;s posting frequency is <strong>at least twice</strong> to their <strong>average</strong> weekly posting frequency for February <code>2024</code>.</p>

<p><strong>Note:</strong> Only include the dates from February <code>1</code> to February <code>28</code> in your analysis, which means you should count February as having exactly <code>4</code> weeks.</p>

<p>Return <em>the result table orderd by </em><code>user_id</code><em> in </em><strong>ascending</strong><em> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Posts table:</p>

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

<p><strong>Output:</strong></p>

<pre class="example-io">
+---------+----------------+------------------+
| user_id | max_7day_posts | avg_weekly_posts |
+---------+----------------+------------------+
| 1       | 1              | 0.2500           |
| 2       | 1              | 0.2500           |
| 5       | 1              | 0.2500           |
+---------+----------------+------------------+
</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>User 1:</strong> Made only 1 post in February, resulting in an average of 0.25 posts per week and a max of 1 post in any 7-day period.</li>
	<li><strong>User 2:</strong> Also made just 1 post, with the same average and max 7-day posting frequency as User 1.</li>
	<li><strong>User 5:</strong> Like Users 1 and 2, User 5 made only 1 post throughout February, leading to the same average and max 7-day posting metrics.</li>
	<li><strong>User 3:</strong> Although User 3 made more posts than the others (3 posts), they did not reach twice the average weekly posts in their consecutive 7-day window, so they are not listed in the output.</li>
</ul>

<p><b>Note:</b> Output table is ordered by user_id in ascending order.</p>
</div>

## 解法

### 方法一：自连接 + 分组统计

我们可以使用自连接，将表 `Posts` 与自身连接，连接条件是 `p1.user_id = p2.user_id` 且 `p2.post_date` 在 `p1.post_date` 和 `p1.post_date` 后 `6` 天之间，然后我们将连接结果按照 `p1.user_id` 和 `p1.post_date` 分组，即可统计出每个用户在每天的 7 天内的发帖数量，我们将这个结果保存在表 `P` 中。

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
        GROUP BY p1.user_id, p1.post_date
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
    # 计算每个用户在7天窗口内发布的帖子数
    p = posts.merge(posts, on='user_id')
    p = p[(p['post_date_y'] >= p['post_date_x']) &
          (p['post_date_y'] <= p['post_date_x'] + pd.Timedelta(days=6))]
    p_count = p.groupby(['user_id', 'post_date_x']).size().reset_index(name='cnt')

    # 计算每个用户在2024年2月期间的平均每周发布的帖子数
    t = posts[(posts['post_date'] >= '2024-02-01') &
              (posts['post_date'] <= '2024-02-28')]
    t_count = t.groupby('user_id').size().reset_index(name='count')
    t_count['avg_weekly_posts'] = t_count['count'] / 4

    # 合并两个计算出的表，并过滤符合条件的用户
    merged_df = p_count.merge(t_count, on='user_id')
    merged_df = merged_df.groupby('user_id').agg(max_7day_posts=('cnt', 'max'),
                                                 avg_weekly_posts=('avg_weekly_posts', 'first'))
    result_df = merged_df[merged_df['max_7day_posts'] >= merged_df['avg_weekly_posts'] * 2].reset_index()

    return result_df.sort_values('user_id')
```

<!-- tabs:end -->

<!-- end -->
