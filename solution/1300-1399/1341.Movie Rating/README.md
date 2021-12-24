# [1341. 电影评分](https://leetcode-cn.com/problems/movie-rating)

[English Version](/solution/1300-1399/1341.Movie%20Rating/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>表：<code>Movies</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| movie_id      | int     |
| title         | varchar |
+---------------+---------+
movie_id 是这个表的主键。
title 是电影的名字。
</pre>

<p>表：<code>Users</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| name          | varchar |
+---------------+---------+
user_id 是表的主键。
</pre>

<p>表：<code>Movie_Rating</code></p>

<pre>+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| movie_id      | int     |
| user_id       | int     |
| rating        | int     |
| created_at    | date    |
+---------------+---------+
(movie_id, user_id) 是这个表的主键。
这个表包含用户在其评论中对电影的评分 rating 。
created_at 是用户的点评日期。 
</pre>

<p>&nbsp;</p>

<p>请你编写一组&nbsp;SQL 查询：</p>

<ul>
	<li>查找评论电影数量最多的用户名。
	<p>如果出现平局，返回字典序较小的用户名。</p>
	</li>
	<li>查找在 <strong>2020 年 2 月 平均评分最高</strong> 的电影名称。
	<p>如果出现平局，返回字典序较小的电影名称。</p>
	</li>
</ul>

<p>查询分两行返回，查询结果格式如下例所示：</p>

<pre>Movies 表：
+-------------+--------------+
| movie_id    |  title       |
+-------------+--------------+
| 1           | Avengers     |
| 2           | Frozen 2     |
| 3           | Joker        |
+-------------+--------------+

Users 表：
+-------------+--------------+
| user_id     |  name        |
+-------------+--------------+
| 1           | Daniel       |
| 2           | Monica       |
| 3           | Maria        |
| 4           | James        |
+-------------+--------------+

Movie_Rating 表：
+-------------+--------------+--------------+-------------+
| movie_id    | user_id      | rating       | created_at  |
+-------------+--------------+--------------+-------------+
| 1           | 1            | 3            | 2020-01-12  |
| 1           | 2            | 4            | 2020-02-11  |
| 1           | 3            | 2            | 2020-02-12  |
| 1           | 4            | 1            | 2020-01-01  |
| 2           | 1            | 5            | 2020-02-17  | 
| 2           | 2            | 2            | 2020-02-01  | 
| 2           | 3            | 2            | 2020-03-01  |
| 3           | 1            | 3            | 2020-02-22  | 
| 3           | 2            | 4            | 2020-02-25  | 
+-------------+--------------+--------------+-------------+

Result 表：
+--------------+
| results      |
+--------------+
| Daniel       |
| Frozen 2     |
+--------------+

Daniel 和 Monica 都点评了 3 部电影（&quot;Avengers&quot;, &quot;Frozen 2&quot; 和 &quot;Joker&quot;） 但是 Daniel 字典序比较小。
Frozen 2 和 Joker 在 2 月的评分都是 3.5，但是 Frozen 2 的字典序比较小。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
