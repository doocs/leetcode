# [3150. Invalid Tweets II 🔒](https://leetcode.com/problems/invalid-tweets-ii)

[中文文档](/solution/3100-3199/3150.Invalid%20Tweets%20II/README.md)

<!-- tags: -->

<!-- difficulty:Easy -->

## Description

<p>Table: <code>Tweets</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| tweet_id       | int     |
| content        | varchar |
+----------------+---------+
tweet_id is the primary key (column with unique values) for this table.
This table contains all the tweets in a social media app.
</pre>

<p>Write a solution to find <strong>invalid tweets</strong>. A tweet is considered invalid if it meets <strong>any</strong> of the following criteria:</p>

<ul>
	<li>It exceeds <code>140</code> characters in length.</li>
	<li>It has more than <code>3</code> mentions.</li>
	<li>It includes more than <code><font face="monospace">3</font></code>&nbsp;hashtags.</li>
</ul>

<p>Return <em>the result table ordered by</em> <code>tweet_id</code> <em>in <strong>ascending</strong> order</em>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example:</strong></p>

<div class="example-block">
<p><strong>Input:</strong></p>

<p>Tweets table:</p>

<pre class="example-io">
  +----------+-----------------------------------------------------------------------------------+
  | tweet_id | content                                                                           |
  +----------+-----------------------------------------------------------------------------------+
  | 1        | Traveling, exploring, and living my best life @JaneSmith @SaraJohnson @LisaTaylor |
  |          | @MikeBrown #Foodie #Fitness #Learning                                             | 
  | 2        | Just had the best dinner with friends! #Foodie #Friends #Fun                      |
  | 4        | Working hard on my new project #Work #Goals #Productivity #Fun                    |
  +----------+-----------------------------------------------------------------------------------+
  </pre>

<p><strong>Output:</strong></p>

<pre class="example-io">
  +----------+
  | tweet_id |
  +----------+
  | 1        |
  | 4        |
  +----------+
  </pre>

<p><strong>Explanation:</strong></p>

<ul>
	<li>tweet_id&nbsp;1 contains 4&nbsp;mentions.</li>
	<li>tweet_id 4 contains 4 hashtags.</li>
</ul>
Output table is ordered by tweet_id in ascending order.</div>

## Solutions

### Solution 1: LENGTH() Function + REPLACE() Function

We can use the `LENGTH()` function to calculate the length of the string, calculate the length after excluding `@` or `#`, then use the `OR` operator to connect these three conditions, filter out the corresponding tweet_id, and sort by tweet_id in ascending order.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT tweet_id
FROM Tweets
WHERE LENGTH(content) > 140
    OR (LENGTH(content) - LENGTH(REPLACE(content, '@', ''))) > 3
    OR (LENGTH(content) - LENGTH(REPLACE(content, '#', ''))) > 3
ORDER BY 1;
```

```python
import pandas as pd


def find_invalid_tweets(tweets: pd.DataFrame) -> pd.DataFrame:
    invalid_tweets = tweets[
        (tweets["content"].str.len() > 140)
        | (tweets["content"].str.count("@") > 3)
        | (tweets["content"].str.count("#") > 3)
    ].sort_values(by="tweet_id")
    return invalid_tweets[["tweet_id"]]
```

<!-- tabs:end -->

<!-- end -->
