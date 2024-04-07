# [3103. 查找热门话题标签 II](https://leetcode.cn/problems/find-trending-hashtags-ii)

[English Version](/solution/3100-3199/3103.Find%20Trending%20Hashtags%20II/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

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

<p>编写一个解决方案来找到&nbsp;<code>2024</code>&nbsp;年 <strong>二月&nbsp;</strong>的 <strong>前</strong>&nbsp;<code>3</code>&nbsp;热门话题 <strong>标签</strong>。每条推文可能含有 <strong>几个标签</strong>。</p>

<p>返回结果表，根据标签的数量和标签&nbsp;<strong>降序</strong> 排序。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong></p>

<p>Tweets 表：</p>

<pre class="example-io">
+---------+----------+------------------------------------------------------------+------------+
| user_id | tweet_id | tweet                                                      | tweet_date |
+---------+----------+------------------------------------------------------------+------------+
| 135     | 13       | Enjoying a great start to the day. #HappyDay #MorningVibes | 2024-02-01 |
| 136     | 14       | Another #HappyDay with good vibes! #FeelGood               | 2024-02-03 |
| 137     | 15       | Productivity peaks! #WorkLife #ProductiveDay               | 2024-02-04 |
| 138     | 16       | Exploring new tech frontiers. #TechLife #Innovation        | 2024-02-04 |
| 139     | 17       | Gratitude for today's moments. #HappyDay #Thankful         | 2024-02-05 |
| 140     | 18       | Innovation drives us. #TechLife #FutureTech                | 2024-02-07 |
| 141     | 19       | Connecting with nature's serenity. #Nature #Peaceful       | 2024-02-09 |
+---------+----------+------------------------------------------------------------+------------+
 </pre>

<p><strong>输出：</strong></p>

<pre class="example-io">
+-----------+-------+
| hashtag   | count |
+-----------+-------+
| #HappyDay | 3     |
| #TechLife | 2     |
| #WorkLife | 1     |
+-----------+-------+

</pre>

<p><strong>解释：</strong></p>

<ul>
	<li><strong>#HappyDay：</strong>在 ID 为 13，14，17 的推文中出现，总共提及&nbsp;3 次。</li>
	<li><strong>#TechLife：</strong>在 ID 为 16，18 的推文中出现，总共提及 2&nbsp;次。</li>
	<li><strong>#WorkLife：</strong>在 ID 为 15 的推文中出现，总共提及 1&nbsp;次。</li>
</ul>

<p><b>注意：</b>输出表分别按 count 和 hashtag 降序排序。</p>
</div>

## 解法

### 方法一：正则匹配

我们可以使用正则表达式来匹配每条推文中的所有标签，然后统计每个标签的出现次数。最后，我们可以按标签出现的次数降序排序，如果出现次数相同，则按标签名称降序排序，返回前三个标签。

<!-- tabs:start -->

```python
import pandas as pd


def find_trending_hashtags(tweets: pd.DataFrame) -> pd.DataFrame:
    # Filter tweets for February 2024
    tweets_feb_2024 = tweets[tweets["tweet_date"].between("2024-02-01", "2024-02-29")]

    # Extract hashtags from tweets
    hashtags = tweets_feb_2024["tweet"].str.findall(r"#\w+")

    # Flatten list of hashtags
    all_hashtags = [tag for sublist in hashtags for tag in sublist]

    # Count occurrences of each hashtag
    hashtag_counts = pd.Series(all_hashtags).value_counts().reset_index()
    hashtag_counts.columns = ["hashtag", "count"]

    # Sort by count of hashtag in descending order
    hashtag_counts = hashtag_counts.sort_values(
        by=["count", "hashtag"], ascending=[False, False]
    )

    # Get top 3 trending hashtags
    top_3_hashtags = hashtag_counts.head(3)

    return top_3_hashtags
```

<!-- tabs:end -->

<!-- end -->
