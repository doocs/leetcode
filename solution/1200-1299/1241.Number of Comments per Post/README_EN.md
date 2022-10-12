# [1241. Number of Comments per Post](https://leetcode.com/problems/number-of-comments-per-post)

[中文文档](/solution/1200-1299/1241.Number%20of%20Comments%20per%20Post/README.md)

## Description

<p>Table: <code>Submissions</code></p>

<pre>
+---------------+----------+
| Column Name   | Type     |
+---------------+----------+
| sub_id        | int      |
| parent_id     | int      |
+---------------+----------+
There is no primary key for this table, it may have duplicate rows.
Each row can be a post or comment on the post.
parent_id is null for posts.
parent_id for comments is <code>sub_id</code> for another post in the table.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to find the number of comments per post. The result table should contain <code>post_id</code> and its corresponding <code>number_of_comments</code>.</p>

<p>The <code>Submissions</code> table may contain duplicate comments. You should count the number of <strong>unique comments</strong> per post.</p>

<p>The <code>Submissions</code> table may contain duplicate posts. You should treat them as one post.</p>

<p>The result table should be <strong>ordered</strong> by <code>post_id</code> in <strong>ascending order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Submissions table:
+---------+------------+
| sub_id  | parent_id  |
+---------+------------+
| 1       | Null       |
| 2       | Null       |
| 1       | Null       |
| 12      | Null       |
| 3       | 1          |
| 5       | 2          |
| 3       | 1          |
| 4       | 1          |
| 9       | 1          |
| 10      | 2          |
| 6       | 7          |
+---------+------------+
<strong>Output:</strong> 
+---------+--------------------+
| post_id | number_of_comments |
+---------+--------------------+
| 1       | 3                  |
| 2       | 2                  |
| 12      | 0                  |
+---------+--------------------+
<strong>Explanation:</strong> 
The post with id 1 has three comments in the table with id 3, 4, and 9. The comment with id 3 is repeated in the table, we counted it <strong>only once</strong>.
The post with id 2 has two comments in the table with id 5 and 10.
The post with id 12 has no comments in the table.
The comment with id 6 is a comment on a deleted post with id 7 so we ignored it.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
