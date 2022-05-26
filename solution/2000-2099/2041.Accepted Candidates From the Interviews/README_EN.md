# [2041. Accepted Candidates From the Interviews](https://leetcode.com/problems/accepted-candidates-from-the-interviews)

[中文文档](/solution/2000-2099/2041.Accepted%20Candidates%20From%20the%20Interviews/README.md)

## Description

<p>Table: <code>Candidates</code></p>

<pre>
+--------------+----------+
| Column Name  | Type     |
+--------------+----------+
| candidate_id | int      |
| name         | varchar  |
| years_of_exp | int      |
| interview_id | int      |
+--------------+----------+
candidate_id is the primary key column for this table.
Each row of this table indicates the name of a candidate, their number of years of experience, and their interview ID.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Rounds</code></p>

<pre>
+--------------+------+
| Column Name  | Type |
+--------------+------+
| interview_id | int  |
| round_id     | int  |
| score        | int  |
+--------------+------+
(interview_id, round_id) is the primary key column for this table.
Each row of this table indicates the score of one round of an interview.
</pre>

<p>&nbsp;</p>

<p>Write an SQL query to report the IDs of the candidates who have <strong>at least two</strong> years of experience and the sum of the score of their interview rounds is <strong>strictly greater than <code>15</code></strong>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The query result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Candidates table:
+--------------+---------+--------------+--------------+
| candidate_id | name    | years_of_exp | interview_id |
+--------------+---------+--------------+--------------+
| 11           | Atticus | 1            | 101          |
| 9            | Ruben   | 6            | 104          |
| 6            | Aliza   | 10           | 109          |
| 8            | Alfredo | 0            | 107          |
+--------------+---------+--------------+--------------+
Rounds table:
+--------------+----------+-------+
| interview_id | round_id | score |
+--------------+----------+-------+
| 109          | 3        | 4     |
| 101          | 2        | 8     |
| 109          | 4        | 1     |
| 107          | 1        | 3     |
| 104          | 3        | 6     |
| 109          | 1        | 4     |
| 104          | 4        | 7     |
| 104          | 1        | 2     |
| 109          | 2        | 1     |
| 104          | 2        | 7     |
| 107          | 2        | 3     |
| 101          | 1        | 8     |
+--------------+----------+-------+
<strong>Output:</strong> 
+--------------+
| candidate_id |
+--------------+
| 9            |
+--------------+
<strong>Explanation:</strong> 
- Candidate 11: The total score is 16, and they have one year of experience. We do not include them in the result table because of their years of experience.
- Candidate 9: The total score is 22, and they have six years of experience. We include them in the result table.
- Candidate 6: The total score is 10, and they have ten years of experience. We do not include them in the result table because the score is not good enough.
- Candidate 8: The total score is 6, and they have zero years of experience. We do not include them in the result table because of their years of experience and the score.
</pre>

## Solutions

<!-- tabs:start -->

### **SQL**

```sql

```

<!-- tabs:end -->
