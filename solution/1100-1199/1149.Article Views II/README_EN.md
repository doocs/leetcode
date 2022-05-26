# [1149. Article Views II](https://leetcode.com/problems/article-views-ii)

[中文文档](/solution/1100-1199/1149.Article%20Views%20II/README.md)

## Description

<p>Table: <code>Views</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| article_id    | int     |
| author_id     | int     |
| viewer_id     | int     |
| view_date     | date    |
+---------------+---------+
There is no primary key for this table, it may have duplicate rows.
Each row of this table indicates that some viewer viewed an article (written by some author) on some date. 
Note that equal author_id and viewer_id indicate the same person.</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find all the people who viewed more than one article on the same date.</p>

<p>Return the result table sorted by <code>id</code> in ascending order.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Views table:
+------------+-----------+-----------+------------+
| article_id | author_id | viewer_id | view_date  |
+------------+-----------+-----------+------------+
| 1          | 3         | 5         | 2019-08-01 |
| 3          | 4         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |
+------------+-----------+-----------+------------+
<strong>Output:</strong> 
+------+
| id   |
+------+
| 5    |
| 6    |
+------+
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    DISTINCT(viewer_id) as id
FROM
    Views
GROUP BY
    view_date, viewer_id
HAVING
    COUNT(DISTINCT(article_id)) > 1
ORDER BY
    id;
```

<!-- tabs:end -->
