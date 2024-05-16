---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3055.Top%20Percentile%20Fraud/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [3055. Top Percentile Fraud 🔒](https://leetcode.com/problems/top-percentile-fraud)

[中文文档](/solution/3000-3099/3055.Top%20Percentile%20Fraud/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Fraud</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| policy_id   | int     |
| state       | varchar |
| fraud_score | int     |
+-------------+---------+
policy_id is column of unique values for this table.
This table contains policy id, state, and fraud score.
</pre>

<p>The Leetcode Insurance Corp has developed an ML-driven <strong>predictive model </strong>to detect the <strong>likelihood</strong> of fraudulent claims. Consequently, they allocate their most seasoned claim adjusters to address the top <code>5%</code> of <strong>claims</strong> <strong>flagged</strong> by this model.</p>

<p>Write a solution to find the top <code>5</code> <strong>percentile</strong> of claims from <strong>each state</strong>.</p>

<p>Return <em>the result table ordered by </em><code>state</code><em> in <strong>ascending</strong> order, </em><code>fraud_score</code><em> in <strong>descending</strong> order, and </em><code>policy_id</code><em> in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Fraud table:
+-----------+------------+-------------+
| policy_id | state      | fraud_score | 
+-----------+------------+-------------+
| 1         | California | 0.92        | 
| 2         | California | 0.68        |   
| 3         | California | 0.17        | 
| 4         | New York   | 0.94        | 
| 5         | New York   | 0.81        | 
| 6         | New York   | 0.77        |  
| 7         | Texas      | 0.98        |  
| 8         | Texas      | 0.97        | 
| 9         | Texas      | 0.96        | 
| 10        | Florida    | 0.97        |  
| 11        | Florida    | 0.98        | 
| 12        | Florida    | 0.78        | 
| 13        | Florida    | 0.88        | 
| 14        | Florida    | 0.66        | 
+-----------+------------+-------------+
<strong>Output:</strong> 
+-----------+------------+-------------+
| policy_id | state      | fraud_score |
+-----------+------------+-------------+
| 1         | California | 0.92        | 
| 11        | Florida    | 0.98        | 
| 4         | New York   | 0.94        | 
| 7         | Texas      | 0.98        |  
+-----------+------------+-------------+
<strong>Explanation</strong>
- For the state of California, only policy ID 1, with a fraud score of 0.92, falls within the top 5 percentile for this state.
- For the state of Florida, only policy ID 11, with a fraud score of 0.98, falls within the top 5 percentile for this state. 
- For the state of New York, only policy ID 4, with a fraud score of 0.94, falls within the top 5 percentile for this state. 
- For the state of Texas, only policy ID 7, with a fraud score of 0.98, falls within the top 5 percentile for this state. 
Output table is ordered by state in ascending order, fraud score in descending order, and policy ID in ascending order.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Using Window Function

We can use the `RANK()` window function to calculate the ranking of fraud scores for each state, then filter out the records with a rank of 1, and sort them as required by the problem.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
WITH
    T AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY state
                ORDER BY fraud_score DESC
            ) AS rk
        FROM Fraud
    )
SELECT policy_id, state, fraud_score
FROM T
WHERE rk = 1
ORDER BY 2, 3 DESC, 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
