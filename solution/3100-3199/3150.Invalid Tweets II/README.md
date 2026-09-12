---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3150.Invalid%20Tweets%20II/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [3150. 无效的推文 II 🔒](https://leetcode.cn/problems/invalid-tweets-ii)

[English Version](/solution/3100-3199/3150.Invalid%20Tweets%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：<code>Tweets</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| tweet_id       | int     |
| content        | varchar |
+----------------+---------+
tweet_id 是这个表的主键（有不同值的列）。
这个表包含某社交媒体 App 中所有的推文。</pre>

<p>编写一个解决方案来找到 <strong>无效的推文</strong>。如果一条推文满足下面 <strong>任一</strong>&nbsp;条件会被认为无效：</p>

<ul>
	<li>长度超过&nbsp;<code>140</code>&nbsp;个字符。</li>
	<li>有超过&nbsp;<code>3</code>&nbsp;次提及。</li>
	<li>有超过&nbsp;<code><font face="monospace">3</font></code>&nbsp;个标签。</li>
</ul>

<p>以&nbsp;<code>tweet_id</code> <em><strong>升序</strong>&nbsp;</em>返回结果表。</p>

<p>查询结果格式如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<div class="example-block">
<p><b>输入：</b></p>

<p>Tweets 表：</p>

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

<p><strong>输出：</strong></p>

<pre class="example-io">
  +----------+
  | tweet_id |
  +----------+
  | 1        |
  | 4        |
  +----------+
  </pre>

<p><strong>解释：</strong></p>

<ul>
	<li>tweet_id&nbsp;1 包含 4&nbsp;次提及。</li>
	<li>tweet_id 4 包含 4 个标签。</li>
</ul>
输出表以 tweet_id 升序排序。</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：LENGTH() 函数 + REPLACE() 函数

<!-- thinking:start -->

> **思考**
>
> 无效推文是长度超过 $140$，或 `@`、`#` 出现超过三次。三条规则互相独立，过滤后再按 $tweet\_id$ 排序即可。
>
> SQL 中可用 `LENGTH` 与去掉符号后的长度差统计出现次数；pandas 则直接对字符串计数。
>
> 因此用长度、`@` 次数与 `#` 次数的析取筛选行，只保留 $tweet\_id$ 并升序输出。

<!-- thinking:end -->

我们可以使用 `LENGTH()` 函数计算字符串的长度，计算排除掉 `@` 或 `#` 之后的长度，然后使用 `OR` 运算符连接这三个条件，筛选出对应的 tweet_id，并按照 tweet_id 升序排序。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT tweet_id
FROM Tweets
WHERE
    LENGTH(content) > 140
    OR (LENGTH(content) - LENGTH(REPLACE(content, '@', ''))) > 3
    OR (LENGTH(content) - LENGTH(REPLACE(content, '#', ''))) > 3
ORDER BY 1;
```

#### Python3

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

<!-- solution:end -->

<!-- problem:end -->
