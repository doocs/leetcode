---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1623.All%20Valid%20Triplets%20That%20Can%20Represent%20a%20Country/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1623. All Valid Triplets That Can Represent a Country 🔒](https://leetcode.com/problems/all-valid-triplets-that-can-represent-a-country)

[中文文档](/solution/1600-1699/1623.All%20Valid%20Triplets%20That%20Can%20Represent%20a%20Country/README.md)

## Description

<!-- description:start -->

<p>Table: <code>SchoolA</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the column with unique values for this table.
Each row of this table contains the name and the id of a student in school A.
All student_name are distinct.
</pre>

<p>&nbsp;</p>

<p>Table: <code>SchoolB</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the column with unique values for this table.
Each row of this table contains the name and the id of a student in school B.
All student_name are distinct.
</pre>

<p>&nbsp;</p>

<p>Table: <code>SchoolC</code></p>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| student_id    | int     |
| student_name  | varchar |
+---------------+---------+
student_id is the column with unique values for this table.
Each row of this table contains the name and the id of a student in school C.
All student_name are distinct.
</pre>

<p>&nbsp;</p>

<p>There is a country with three schools, where each student is enrolled in <strong>exactly one</strong> school. The country is joining a competition and wants to select one student from each school to represent the country such that:</p>

<ul>
	<li><code>member_A</code> is selected from <code>SchoolA</code>,</li>
	<li><code>member_B</code> is selected from <code>SchoolB</code>,</li>
	<li><code>member_C</code> is selected from <code>SchoolC</code>, and</li>
	<li>The selected students&#39; names and IDs are pairwise distinct (i.e. no two students share the same name, and no two students share the same ID).</li>
</ul>

<p>Write a solution to find all the possible triplets representing the country under the given constraints.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
SchoolA table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 1          | Alice        |
| 2          | Bob          |
+------------+--------------+
SchoolB table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 3          | Tom          |
+------------+--------------+
SchoolC table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 3          | Tom          |
| 2          | Jerry        |
| 10         | Alice        |
+------------+--------------+
<strong>Output:</strong> 
+----------+----------+----------+
| member_A | member_B | member_C |
+----------+----------+----------+
| Alice    | Tom      | Jerry    |
| Bob      | Tom      | Alice    |
+----------+----------+----------+
<strong>Explanation:</strong> 
Let us see all the possible triplets.
- (Alice, Tom, Tom) --&gt; Rejected because member_B and member_C have the same name and the same ID.
- (Alice, Tom, Jerry) --&gt; Valid triplet.
- (Alice, Tom, Alice) --&gt; Rejected because member_A and member_C have the same name.
- (Bob, Tom, Tom) --&gt; Rejected because member_B and member_C have the same name and the same ID.
- (Bob, Tom, Jerry) --&gt; Rejected because member_A and member_C have the same ID.
- (Bob, Tom, Alice) --&gt; Valid triplet.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    a.student_name AS member_A,
    b.student_name AS member_B,
    c.student_name AS member_C
FROM
    SchoolA AS a,
    SchoolB AS b,
    SchoolC AS c
WHERE
    a.student_name != b.student_name
    AND a.student_name != c.student_name
    AND b.student_name != c.student_name
    AND a.student_id != b.student_id
    AND a.student_id != c.student_id
    AND b.student_id != c.student_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
