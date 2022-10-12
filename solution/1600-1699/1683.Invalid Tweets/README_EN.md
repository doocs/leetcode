# [1683. Invalid Tweets](https://leetcode.com/problems/invalid-tweets)

[中文文档](/solution/1600-1699/1683.Invalid%20Tweets/README.md)

## Description

<p>Table: <code>Tweets</code></p>

<pre>
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| tweet_id       | int     |
| content        | varchar |
+----------------+---------+
tweet_id is the primary key for this table.
This table contains all the tweets in a social media app.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the IDs of the invalid tweets. The tweet is invalid if the number of characters used in the content of the tweet is <strong>strictly greater</strong> than <code>15</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Tweets table:
+----------+----------------------------------+
| tweet_id | content                          |
+----------+----------------------------------+
| 1        | Vote for Biden                   |
| 2        | Let us make America great again! |
+----------+----------------------------------+
<strong>Output:</strong> 
+----------+
| tweet_id |
+----------+
| 2        |
+----------+
<strong>Explanation:</strong> 
Tweet 1 has length = 14. It is a valid tweet.
Tweet 2 has length = 32. It is an invalid tweet.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    tweet_id
FROM
    Tweets
WHERE
    CHAR_LENGTH(content) > 15;
```

<!-- tabs:end -->
