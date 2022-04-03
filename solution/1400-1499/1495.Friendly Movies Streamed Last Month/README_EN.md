# [1495. Friendly Movies Streamed Last Month](https://leetcode.com/problems/friendly-movies-streamed-last-month)

[中文文档](/solution/1400-1499/1495.Friendly%20Movies%20Streamed%20Last%20Month/README.md)

## Description

<p>Table: <code>TVProgram</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| program_date  | date    |
| content_id    | int     |
| channel       | varchar |
+---------------+---------+
(program_date, content_id) is the primary key for this table.
This table contains information of the programs on the TV.
content_id is the id of the program in some channel on the TV.</pre>

<p>&nbsp;</p>

<p>Table: <code>Content</code></p>

<pre>
+------------------+---------+
| Column Name      | Type    |
+------------------+---------+
| content_id       | varchar |
| title            | varchar |
| Kids_content     | enum    |
| content_type     | varchar |
+------------------+---------+
content_id is the primary key for this table.
Kids_content is an enum that takes one of the values (&#39;Y&#39;, &#39;N&#39;) where: 
&#39;Y&#39; means is content for kids otherwise &#39;N&#39; is not content for kids.
content_type is the category of the content as movies, series, etc.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the distinct titles of the kid-friendly movies streamed in <strong>June 2020</strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
TVProgram table:
+--------------------+--------------+-------------+
| program_date       | content_id   | channel     |
+--------------------+--------------+-------------+
| 2020-06-10 08:00   | 1            | LC-Channel  |
| 2020-05-11 12:00   | 2            | LC-Channel  |
| 2020-05-12 12:00   | 3            | LC-Channel  |
| 2020-05-13 14:00   | 4            | Disney Ch   |
| 2020-06-18 14:00   | 4            | Disney Ch   |
| 2020-07-15 16:00   | 5            | Disney Ch   |
+--------------------+--------------+-------------+
Content table:
+------------+----------------+---------------+---------------+
| content_id | title          | Kids_content  | content_type  |
+------------+----------------+---------------+---------------+
| 1          | Leetcode Movie | N             | Movies        |
| 2          | Alg. for Kids  | Y             | Series        |
| 3          | Database Sols  | N             | Series        |
| 4          | Aladdin        | Y             | Movies        |
| 5          | Cinderella     | Y             | Movies        |
+------------+----------------+---------------+---------------+
<strong>Output:</strong> 
+--------------+
| title        |
+--------------+
| Aladdin      |
+--------------+
<strong>Explanation:</strong> 
&quot;Leetcode Movie&quot; is not a content for kids.
&quot;Alg. for Kids&quot; is not a movie.
&quot;Database Sols&quot; is not a movie
&quot;Alladin&quot; is a movie, content for kids and was streamed in June 2020.
&quot;Cinderella&quot; was not streamed in June 2020.
</pre>

## Solutions

<!-- tabs:start -->

```sql
SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    content_type = 'Movies'
        AND kids_content = 'Y'
        AND program_date BETWEEN '2020-06-01' AND '2020-06-30';
```

```sql
SELECT DISTINCT
    title
FROM
    Content
        INNER JOIN
    TVProgram ON Content.content_id = TVProgram.content_id
WHERE
    kids_content = 'Y'
        AND (MONTH(program_date) , YEAR(program_date)) = (6 , 2020);
```

<!-- tabs:end -->
