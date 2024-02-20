# [2990. Loan Types](https://leetcode.com/problems/loan-types)

[中文文档](/solution/2900-2999/2990.Loan%20Types/README.md)

<!-- tags:Database -->

## Description

<p>Table: <code>Loans</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| loan_id     | int     |
| user_id     | int     |
| loan_type   | varchar |
+-------------+---------+
loan_id is column of unique values for this table.
This table contains loan_id, user_id, and loan_type.
</pre>

<p>Write a solution to find all <strong>distinct</strong> <code>user_id</code>&#39;s that have <strong>at least one</strong> <strong>Refinance</strong> loan type and at least one <strong>Mortgage</strong> loan type.</p>

<p>Return <em>the result table ordered by </em><code>user_id</code><em> in <strong>ascending</strong> order</em><em>.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
Loans table:
+---------+---------+-----------+
| loan_id | user_id | loan_type |
+---------+---------+-----------+
| 683     | 101     | Mortgage  |
| 218     | 101     | AutoLoan  |
| 802     | 101     | Inschool  |
| 593     | 102     | Mortgage  |
| 138     | 102     | Refinance |
| 294     | 102     | Inschool  |
| 308     | 103     | Refinance |
| 389     | 104     | Mortgage  |
+---------+---------+-----------+
<strong>Output</strong>
+---------+
| user_id | 
+---------+
| 102     | 
+---------+
<strong>Explanation</strong>
- User_id 101 has three loan types, one of which is a Mortgage. However, this user does not have any loan type categorized as Refinance, so user_id 101 won&#39;t be considered.
- User_id 102 possesses three loan types: one for Mortgage and one for Refinance. Hence, user_id 102 will be included in the result.
- User_id 103 has a loan type of Refinance but lacks a Mortgage loan type, so user_id 103 won&#39;t be considered.
- User_id 104 has a Mortgage loan type but doesn&#39;t have a Refinance loan type, thus, user_id 104 won&#39;t be considered.
Output table is ordered by user_id in ascending order.
</pre>

## Solutions

### Solution 1: Grouping and Summation

We can group the `Loans` table by `user_id` to find users who have both `Refinance` and `Mortgage`. Then, sort the results by `user_id`.

<!-- tabs:start -->

```sql
# Write your MySQL query statement below
SELECT user_id
FROM Loans
GROUP BY 1
HAVING SUM(loan_type = 'Refinance') > 0 AND SUM(loan_type = 'Mortgage') > 0
ORDER BY 1;
```

<!-- tabs:end -->

<!-- end -->
