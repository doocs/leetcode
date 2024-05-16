---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3087.Find%20Trending%20Hashtags/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3087. 查找热门话题标签 🔒](https://leetcode.cn/problems/find-trending-hashtags)

[English Version](/solution/3000-3099/3087.Find%20Trending%20Hashtags/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Tweets</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| tweet_id    | int     |
| tweet_date  | date    |
| tweet       | varchar |
+-------------+---------+
tweet_id 是这张表的主键 (值互不相同的列)。
这张表的每一行都包含 user_id, tweet_id, tweet_date 和 tweet。
</pre>

<p>编写一个解决方案来找到&nbsp;<code>2024</code>&nbsp;年 <strong>二月&nbsp;</strong>的 <strong>前</strong>&nbsp;<code>3</code>&nbsp;热门话题 <strong>标签</strong>。每条推文只包含一个标签。</p>

<p>返回结果表，根据标签的数量和名称&nbsp;<strong>降序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><b>输入：</b></p>

<p>Tweets 表：</p>

<pre class="example-io">
+---------+----------+----------------------------------------------+------------+
| user_id | tweet_id | tweet                                        | tweet_date |
+---------+----------+----------------------------------------------+------------+
| 135     | 13       | Enjoying a great start to the day! #HappyDay | 2024-02-01 |
| 136     | 14       | Another #HappyDay with good vibes!           | 2024-02-03 |
| 137     | 15       | Productivity peaks! #WorkLife                | 2024-02-04 |
| 138     | 16       | Exploring new tech frontiers. #TechLife      | 2024-02-04 |
| 139     | 17       | Gratitude for today's moments. #HappyDay     | 2024-02-05 |
| 140     | 18       | Innovation drives us. #TechLife              | 2024-02-07 |
| 141     | 19       | Connecting with nature's serenity. #Nature   | 2024-02-09 |
+---------+----------+----------------------------------------------+------------+
 </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+--------------+
| hashtag   | hashtag_count|
+-----------+--------------+
| #HappyDay | 3            |
| #TechLife | 2            |
| #WorkLife | 1            |
+-----------+--------------+

</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>#HappyDay：</strong>在 ID 为 13，14，17 的推文中出现，总共提及&nbsp;3 次。</li>
	<li><strong>#TechLife：</strong>在 ID 为 16，18 的推文中出现，总共提及 2&nbsp;次。</li>
	<li><strong>#WorkLife：</strong>在 ID 为 15 的推文中出现，总共提及 1&nbsp;次。</li>
</ul>

<p><b>注意：</b>输出表分别按 hashtag_count 和 hashtag 降序排序。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：提取子串 + 分组

我们可以查询得到 2024 年 2 月的所有 tweet，利用 `SUBSTRING_INDEX` 函数提取 Hashtag，然后使用 `GROUP BY` 和 `COUNT` 函数统计每个 Hashtag 出现的次数，最后按照出现次数降序、Hashtag 降序排序，取前三个热门 Hashtag。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    CONCAT('#', SUBSTRING_INDEX(SUBSTRING_INDEX(tweet, '#', -1), ' ', 1)) AS hashtag,
    COUNT(1) AS hashtag_count
FROM Tweets
WHERE DATE_FORMAT(tweet_date, '%Y%m') = '202402'
GROUP BY 1
ORDER BY 2 DESC, 1 DESC
LIMIT 3;
```

```python
import pandas as pd


def find_trending_hashtags(tweets: pd.DataFrame) -> pd.DataFrame:
    # 过滤数据框以获取特定日期的数据
    tweets = tweets[tweets["tweet_date"].dt.strftime("%Y%m") == "202402"]

    # 提取 Hashtag
    tweets["hashtag"] = "#" + tweets["tweet"].str.extract(r"#(\w+)")

    # 统计 Hashtag 出现次数
    hashtag_counts = tweets["hashtag"].value_counts().reset_index()
    hashtag_counts.columns = ["hashtag", "hashtag_count"]

    # 根据出现次数降序排序 Hashtag
    hashtag_counts = hashtag_counts.sort_values(
        by=["hashtag_count", "hashtag"], ascending=[False, False]
    )

    # 返回前三个热门 Hashtag
    top_3_hashtags = hashtag_counts.head(3)

    return top_3_hashtags
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
