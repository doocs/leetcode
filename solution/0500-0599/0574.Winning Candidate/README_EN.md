# [574. Winning Candidate](https://leetcode.com/problems/winning-candidate)

[中文文档](/solution/0500-0599/0574.Winning%20Candidate/README.md)

## Description

<p>Table: <code>Candidate</code></p>

<pre>
+-----+---------+
| id  | Name    |
+-----+---------+
| 1   | A       |
| 2   | B       |
| 3   | C       |
| 4   | D       |
| 5   | E       |
+-----+---------+  
</pre>

<p>Table: <code>Vote</code></p>

<pre>
+-----+--------------+
| id  | CandidateId  |
+-----+--------------+
| 1   |     2        |
| 2   |     4        |
| 3   |     3        |
| 4   |     2        |
| 5   |     5        |
+-----+--------------+
id is the auto-increment primary key,
CandidateId is the id appeared in Candidate table.
</pre>

<p>Write a sql to find the name of the winning candidate, the above example will return the winner <code>B</code>.</p>

<pre>
+------+
| Name |
+------+
| B    |
+------+
</pre>

<p><b>Notes:</b></p>

<ol>
	<li>You may assume <b>there is no tie</b>, in other words there will be <b>only one</b> winning candidate.</li>
</ol>

<p>&nbsp;</p>


## Solutions

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
SELECT
    Name
FROM
    (
        SELECT
            CandidateId AS id
        FROM
            Vote
        GROUP BY
            CandidateId
        ORDER BY
            COUNT(id) DESC
        LIMIT 1
    ) AS t
INNER JOIN
    Candidate c
ON
    t.id = c.id;
```

<!-- tabs:end -->

