# [1683. 无效的推文](https://leetcode.cn/problems/invalid-tweets)

[English Version](/solution/1600-1699/1683.Invalid%20Tweets/README_EN.md)

<!-- tags:数据库 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Tweets</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| tweet_id       | int     |
| content        | varchar |
+----------------+---------+
在 SQL 中，tweet_id 是这个表的主键。
这个表包含某社交媒体 App 中所有的推文。</pre>

<p>&nbsp;</p>

<p>查询所有无效推文的编号（ID）。当推文内容中的字符数<strong>严格大于</strong> <code>15</code> 时，该推文是无效的。</p>

<p>以<strong>任意顺序</strong>返回结果表。</p>

<p>查询结果格式如下所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Tweets 表：
+----------+----------------------------------+
| tweet_id | content                          |
+----------+----------------------------------+
| 1        | Vote for Biden                   |
| 2        | Let us make America great again! |
+----------+----------------------------------+

<strong>输出：</strong>
+----------+
| tweet_id |
+----------+
| 2        |
+----------+
<strong>解释：</strong>
推文 1 的长度 length = 14。该推文是有效的。
推文 2 的长度 length = 32。该推文是无效的。
</pre>

## 解法

### 方法一：使用 `CHAR_LENGTH` 函数

`CHAR_LENGTH()` 函数返回字符串的长度，其中中文、数字、字母都是 $1$ 字节。

`LENGTH()` 函数返回字符串的长度，其中 utf8 编码下，中文 $3$ 字节，数字、字母 $1$ 字节；gbk 编码下，中文 $2$ 字节，数字、字母 $1$ 字节。

对于本题，我们直接用 `CHAR_LENGTH` 函数获取字符串长度，筛选出长度大于 $15$ 的推文 ID 即可。

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    tweet_id
FROM Tweets
WHERE CHAR_LENGTH(content) > 15;
```

<!-- tabs:end -->

<!-- end -->
