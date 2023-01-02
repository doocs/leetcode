# [2504. Concatenate the Name and the Profession](https://leetcode.com/problems/concatenate-the-name-and-the-profession)

[中文文档](/solution/2500-2599/2504.Concatenate%20the%20Name%20and%20the%20Profession/README.md)

## Description

<p>Table: <code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| person_id   | int     |
| name        | varchar |
| profession  | ENUM    |
+-------------+---------+
person_id is the primary key for this table.
Each row in this table contains a person&#39;s ID, name, and profession.
The profession   column in an enum of the type (&#39;Doctor&#39;, &#39;Singer&#39;, &#39;Actor&#39;, &#39;Player&#39;, &#39;Engineer&#39;, or &#39;Lawyer&#39;)
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report each person&#39;s name followed by the first letter of their profession enclosed in parentheses.</p>

<p>Return the result table <strong>ordered</strong> by <code>person_id</code> in <strong>descending order</strong>.</p>

<p>The query result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Person table:
+-----------+-------+------------+
| person_id | name  | profession |
+-----------+-------+------------+
| 1         | Alex  | Singer     |
| 3         | Alice | Actor      |
| 2         | Bob   | Player     |
| 4         | Messi | Doctor     |
| 6         | Tyson | Engineer   |
| 5         | Meir  | Lawyer     |
+-----------+-------+------------+
<strong>Output:</strong> 
+-----------+----------+
| person_id | name     |
+-----------+----------+
| 6         | Tyson(E) |
| 5         | Meir(L)  |
| 4         | Messi(D) |
| 3         | Alice(A) |
| 2         | Bob(P)   |
| 1         | Alex(S)  |
+-----------+----------+
<strong>Explanation:</strong> Note that there should not be any white space between the name and the first letter of the profession.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT person_id, CONCAT(name, "(", substring(profession, 1, 1), ")") AS name
FROM Person
ORDER BY person_id DESC;
```

<!-- tabs:end -->
