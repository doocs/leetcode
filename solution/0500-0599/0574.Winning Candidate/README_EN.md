# [574. Winning Candidate 🔒](https://leetcode.com/problems/winning-candidate)

[中文文档](/solution/0500-0599/0574.Winning%20Candidate/README.md)

<!-- tags:Database -->

<!-- difficulty:Medium -->

## Description

<p>Table: <code>Candidate</code></p>

<pre>
+-------------+----------+
| Column Name | Type     |
+-------------+----------+
| id          | int      |
| name        | varchar  |
+-------------+----------+
id is the column with unique values for this table.
Each row of this table contains information about the id and the name of a candidate.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Vote</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| candidateId | int  |
+-------------+------+
id is an auto-increment primary key (column with unique values).
candidateId is a foreign key (reference column) to id from the Candidate table.
Each row of this table determines the candidate who got the i<sup>th</sup> vote in the elections.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the name of the winning candidate (i.e., the candidate who got the largest number of votes).</p>

<p>The test cases are generated so that <strong>exactly one candidate wins</strong> the elections.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Candidate table:
+----+------+
| id | name |
+----+------+
| 1  | A    |
| 2  | B    |
| 3  | C    |
| 4  | D    |
| 5  | E    |
+----+------+
Vote table:
+----+-------------+
| id | candidateId |
+----+-------------+
| 1  | 2           |
| 2  | 4           |
| 3  | 3           |
| 4  | 2           |
| 5  | 5           |
+----+-------------+
<strong>Output:</strong> 
+------+
| name |
+------+
| B    |
+------+
<strong>Explanation:</strong> 
Candidate B has 2 votes. Candidates C, D, and E have 1 vote each.
The winner is candidate B.
</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT
    Name
FROM
    (
        SELECT
            CandidateId AS id
        FROM Vote
        GROUP BY CandidateId
        ORDER BY COUNT(id) DESC
        LIMIT 1
    ) AS t
    INNER JOIN Candidate AS c ON t.id = c.id;
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT name
FROM
    Candidate AS c
    LEFT JOIN Vote AS v ON c.id = v.candidateId
GROUP BY c.id
ORDER BY COUNT(1) DESC
LIMIT 1;
```

<!-- tabs:end -->

<!-- end -->
