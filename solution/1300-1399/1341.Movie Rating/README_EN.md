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

<p>Table: <code>Movie_Rating</code></p>

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

<p>Write the following SQL query:</p>

<ul>
	<li>Find the name of the user who has rated the greatest number of movies.
	<p>In case of a tie, return&nbsp;lexicographically smaller user name.</p>
	</li>
	<li>Find the movie name with the <em><strong>highest average</strong></em> rating in&nbsp;<strong>February&nbsp;2020</strong>.
	<p>In case of a tie, return lexicographically smaller movie name.</p>
	</li>
</ul>

<p>The query is returned in 2 rows, the query result format is in the following example:</p>

<pre>
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

Movie_Rating table:
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

Result table:
+--------------+
| results      |
+--------------+
| Daniel       |
| Frozen 2     |
+--------------+

Daniel and Monica have rated 3 movies (&quot;Avengers&quot;, &quot;Frozen 2&quot; and &quot;Joker&quot;) but Daniel is&nbsp;smaller lexicographically.
Frozen 2 and Joker have a rating average of 3.5 in February but Frozen 2 is&nbsp;smaller lexicographically.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
