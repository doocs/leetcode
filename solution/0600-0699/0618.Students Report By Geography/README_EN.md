# [618. Students Report By Geography](https://leetcode.com/problems/students-report-by-geography)

[中文文档](/solution/0600-0699/0618.Students%20Report%20By%20Geography/README.md)

## Description

<p>Table: <code>Student</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| name        | varchar |
| continent   | varchar |
+-------------+---------+
There is no primary key for this table. It may contain duplicate rows.
Each row of this table indicates the name of a student and the continent they came from.
</pre>

<p>&nbsp;</p>

<p>A school has students from Asia, Europe, and America.</p>

<p>Write an SQL query to <a href="https://en.wikipedia.org/wiki/Pivot_table" target="_blank">pivot</a> the continent column in the <code>Student</code> table so that each name is <strong>sorted alphabetically</strong> and displayed underneath its corresponding continent. The output headers should be <code>America</code>, <code>Asia</code>, and <code>Europe</code>, respectively.</p>

<p>The test cases are generated so that the student number from America is not less than either Asia or Europe.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Student table:
+--------+-----------+
| name   | continent |
+--------+-----------+
| Jane   | America   |
| Pascal | Europe    |
| Xi     | Asia      |
| Jack   | America   |
+--------+-----------+
<strong>Output:</strong> 
+---------+------+--------+
| America | Asia | Europe |
+---------+------+--------+
| Jack    | Xi   | Pascal |
| Jane    | null | null   |
+---------+------+--------+
</pre>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If it is unknown which continent has the most students, could you write a query to generate the student report?</p>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
