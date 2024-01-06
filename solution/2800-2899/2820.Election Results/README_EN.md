# [2820. Election Results](https://leetcode.com/problems/election-results)

[中文文档](/solution/2800-2899/2820.Election%20Results/README.md)

## Description

<p>Table: <code><font face="monospace">Votes</font></code></p>

<pre>
+-------------+---------+ 
| Column Name | Type    | 
+-------------+---------+ 
| voter       | varchar | 
| candidate   | varchar |
+-------------+---------+
(voter, candidate) is the primary key (combination of unique values) for this table.
Each row of this table contains name of the voter and their candidate. 
</pre>

<p>The election is conducted in a city where everyone can vote for <strong>one or more</strong> candidates or choose <strong>not</strong> to vote. Each person has <code>1</code><strong> vote</strong> so if they vote for multiple candidates, their vote gets equally split across them. For example, if a person votes for <code>2</code> candidates, these candidates receive an equivalent of <code>0.5</code>&nbsp;votes each.</p>

<p>Write a solution to find <code>candidate</code> who got the most votes and won the election. Output the name of the <strong>candidate</strong> or If multiple candidates have an <strong>equal number</strong> of votes, display the names of all of them.</p>

<p>Return<em> the result table ordered</em> <em>by</em> <code>candidate</code> <em>in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Votes table:
+----------+-----------+
| voter    | candidate |
+----------+-----------+
| Kathy    | null      |
| Charles  | Ryan      |
| Charles  | Christine |
| Charles  | Kathy     |
| Benjamin | Christine |
| Anthony  | Ryan      |
| Edward   | Ryan      |
| Terry    | null      |
| Evelyn   | Kathy     |
| Arthur   | Christine |
+----------+-----------+
<strong>Output:</strong> 
+-----------+
| candidate | 
+-----------+
| Christine |  
| Ryan      |  
+-----------+
<strong>Explanation:</strong> 
- Kathy and Terry opted not to participate in voting, resulting in their votes being recorded as 0. Charles distributed his vote among three candidates, equating to 0.33 for each candidate. On the other hand, Benjamin, Arthur, Anthony, Edward, and Evelyn each cast their votes for a single candidate.
- Collectively, Candidate Ryan and Christine amassed a total of 2.33 votes, while Kathy received a combined total of 1.33 votes.
Since Ryan and Christine received an equal number of votes, we will display their names in ascending order.</pre>

## Solutions

**Solution 1: Window Function + Group Statistics**

We can use the window function `count` to calculate the number of votes each voter gives to the candidates, then use the group statistics function `sum` to calculate the total number of votes for each candidate. Next, we use the window function `rank` to calculate the ranking of each candidate, and finally filter out the candidate who ranks first.

Note that there may be multiple candidates ranking first in the result set, so we need to use `order by` to sort the candidates.

<!-- tabs:start -->

### **SQL**

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT candidate, SUM(vote) AS tot
        FROM
            (
                SELECT
                    candidate,
                    1 / (COUNT(candidate) OVER (PARTITION BY voter)) AS vote
                FROM Votes
                WHERE candidate IS NOT NULL
            ) AS t
        GROUP BY 1
    ),
    P AS (
        SELECT
            candidate,
            RANK() OVER (ORDER BY tot DESC) AS rk
        FROM T
    )
SELECT candidate
FROM P
WHERE rk = 1
ORDER BY 1;
```

<!-- tabs:end -->
