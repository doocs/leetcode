---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3103.Find%20Trending%20Hashtags%20II/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3103. Find Trending Hashtags II 🔒](https://leetcode.com/problems/find-trending-hashtags-ii)

[中文文档](/solution/3100-3199/3103.Find%20Trending%20Hashtags%20II/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Tweets</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| tweet_id    | int     |
| tweet_date  | date    |
| tweet       | varchar |
+-------------+---------+
tweet_id is the primary key (column with unique values) for this table.
Each row of this table contains user_id, tweet_id, tweet_date and tweet.
</pre>

<p>Write a solution to find the <strong>top</strong> <code>3</code> trending <strong>hashtags</strong> in <strong>February</strong> <code>2024</code>. Every tweet may contain <strong>several</strong> <strong>hashtags</strong>.</p>

<p>Return <em>the result table orderd by count of hashtag, hashtag in </em><strong>descending</strong><em> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Tweets table:</p>

<pre class="example-io">
+---------+----------+------------------------------------------------------------+------------+
| user_id | tweet_id | tweet                                                      | tweet_date |
+---------+----------+------------------------------------------------------------+------------+
| 135     | 13       | Enjoying a great start to the day. #HappyDay #MorningVibes | 2024-02-01 |
| 136     | 14       | Another #HappyDay with good vibes! #FeelGood               | 2024-02-03 |
| 137     | 15       | Productivity peaks! #WorkLife #ProductiveDay               | 2024-02-04 |
| 138     | 16       | Exploring new tech frontiers. #TechLife #Innovation        | 2024-02-04 |
| 139     | 17       | Gratitude for today&#39;s moments. #HappyDay #Thankful         | 2024-02-05 |
| 140     | 18       | Innovation drives us. #TechLife #FutureTech                | 2024-02-07 |
| 141     | 19       | Connecting with nature&#39;s serenity. #Nature #Peaceful       | 2024-02-09 |
+---------+----------+------------------------------------------------------------+------------+
 </pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
+-----------+-------+
| hashtag   | count |
+-----------+-------+
| #HappyDay | 3     |
| #TechLife | 2     |
| #WorkLife | 1     |
+-----------+-------+

</pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>#HappyDay:</strong> Appeared in tweet IDs 13, 14, and 17, with a total count of 3 mentions.</li>
	<li><strong>#TechLife:</strong> Appeared in tweet IDs 16 and 18, with a total count of 2 mentions.</li>
	<li><strong>#WorkLife:</strong> Appeared in tweet ID 15, with a total count of 1 mention.</li>
</ul>

<p><b>Note:</b> Output table is sorted in descending order by count and hashtag respectively.</p>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Regular Expression Matching

We can use regular expressions to match all tags in each tweet, and then count the occurrence of each tag. Finally, we can sort the tags in descending order by the number of occurrences. If the number of occurrences is the same, we sort them in descending order by the tag name, and return the top three tags.

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

<!-- solution:end -->

<!-- problem:end -->
