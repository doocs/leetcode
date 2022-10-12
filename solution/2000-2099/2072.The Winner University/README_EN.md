# [2072. The Winner University](https://leetcode.com/problems/the-winner-university)

[中文文档](/solution/2000-2099/2072.The%20Winner%20University/README.md)

## Description

<p>Table: <code>NewYork</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
student_id is the primary key for this table.
Each row contains information about the score of one student from New York University in an exam.
</pre>

<p>&nbsp;</p>

<p>Table: <code>California</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| student_id  | int  |
| score       | int  |
+-------------+------+
student_id is the primary key for this table.
Each row contains information about the score of one student from California University in an exam.
</pre>

<p>&nbsp;</p>

<p>There is a competition between New York University and California University. The competition is held between the same number of students from both universities. The university that has more <strong>excellent students</strong> wins the competition. If the two universities have the same number of <strong>excellent students</strong>, the competition ends in a draw.</p>

<p>An <strong>excellent student</strong> is a student that scored <code>90%</code> or more in the exam.</p>

<p>Write an SQL query to report:</p>

<ul>
	<li><strong>&quot;New York University&quot;</strong> if New York University wins the competition.</li>
	<li><strong>&quot;California University&quot;</strong> if California University wins the competition.</li>
	<li><strong>&quot;No Winner&quot;</strong> if the competition ends in a draw.</li>
</ul>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
NewYork table:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 90    |
| 2          | 87    |
+------------+-------+
California table:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 89    |
| 3          | 88    |
+------------+-------+
<strong>Output:</strong> 
+---------------------+
| winner              |
+---------------------+
| New York University |
+---------------------+
<strong>Explanation:</strong>
New York University has 1 excellent student, and California University has 0 excellent students.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
NewYork table:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 89    |
| 2          | 88    |
+------------+-------+
California table:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 90    |
| 3          | 87    |
+------------+-------+
<strong>Output:</strong> 
+-----------------------+
| winner                |
+-----------------------+
| California University |
+-----------------------+
<strong>Explanation:</strong>
New York University has 0 excellent students, and California University has 1 excellent student.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
NewYork table:
+------------+-------+
| student_id | score |
+------------+-------+
| 1          | 89    |
| 2          | 90    |
+------------+-------+
California table:
+------------+-------+
| student_id | score |
+------------+-------+
| 2          | 87    |
| 3          | 99    |
+------------+-------+
<strong>Output:</strong> 
+-----------+
| winner    |
+-----------+
| No Winner |
+-----------+
<strong>Explanation:</strong>
Both New York University and California University have 1 excellent student.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    CASE
        WHEN n1.cnt > n2.cnt THEN 'New York University'
        WHEN n1.cnt < n2.cnt THEN 'California University'
        ELSE 'No Winner'
    END AS winner
FROM
    (SELECT count(1) cnt FROM NewYork WHERE score >= 90) n1,
    (SELECT COUNT(1) cnt FROM California WHERE score >= 90) n2;
```

<!-- tabs:end -->
