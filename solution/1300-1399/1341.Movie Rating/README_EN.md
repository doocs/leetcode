# [1341. Movie Rating](https://leetcode.com/problems/movie-rating)

[中文文档](/solution/1300-1399/1341.Movie%20Rating/README.md)

## Description

<p>Table: <code>Movies</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| movie_id      | int     |
| title         | varchar |
+---------------+---------+
movie_id is the primary key for this table.
title is the name of the movie.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Users</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| user_id       | int     |
| name          | varchar |
+---------------+---------+
user_id is the primary key for this table.
</pre>

<p>&nbsp;</p>

<p>Table: <code>MovieRating</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| movie_id      | int     |
| user_id       | int     |
| rating        | int     |
| created_at    | date    |
+---------------+---------+
(movie_id, user_id) is the primary key for this table.
This table contains the rating of a movie by a user in their review.
created_at is the user&#39;s review date. 
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to:</p>

<ul>
	<li>Find the name of the user who has rated the greatest number of movies. In case of a tie, return the lexicographically smaller user name.</li>
	<li>Find the movie name with the <strong>highest average</strong> rating in <code>February 2020</code>. In case of a tie, return the lexicographically smaller movie name.</li>
</ul>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Movies table:
+-------------+--------------+
| movie_id    |  title       |
+-------------+--------------+
| 1           | Avengers     |
| 2           | Frozen 2     |
| 3           | Joker        |
+-------------+--------------+
Users table:
+-------------+--------------+
| user_id     |  name        |
+-------------+--------------+
| 1           | Daniel       |
| 2           | Monica       |
| 3           | Maria        |
| 4           | James        |
+-------------+--------------+
MovieRating table:
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
<strong>Output:</strong> 
+--------------+
| results      |
+--------------+
| Daniel       |
| Frozen 2     |
+--------------+
<strong>Explanation:</strong> 
Daniel and Monica have rated 3 movies (&quot;Avengers&quot;, &quot;Frozen 2&quot; and &quot;Joker&quot;) but Daniel is smaller lexicographically.
Frozen 2 and Joker have a rating average of 3.5 in February but Frozen 2 is smaller lexicographically.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
