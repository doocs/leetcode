---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1907.Count%20Salary%20Categories/README_EN.md
tags:
    - Database
---

<!-- problem:start -->

# [1907. Count Salary Categories](https://leetcode.com/problems/count-salary-categories)

[中文文档](/solution/1900-1999/1907.Count%20Salary%20Categories/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Accounts</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| account_id  | int  |
| income      | int  |
+-------------+------+
account_id is the primary key (column with unique values) for this table.
Each row contains information about the monthly income for one bank account.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to calculate the number of bank accounts for each salary category. The salary categories are:</p>

<ul>
	<li><code>&quot;Low Salary&quot;</code>: All the salaries <strong>strictly less</strong> than <code>$20000</code>.</li>
	<li><code>&quot;Average Salary&quot;</code>: All the salaries in the <strong>inclusive</strong> range <code>[$20000, $50000]</code>.</li>
	<li><code>&quot;High Salary&quot;</code>: All the salaries <strong>strictly greater</strong> than <code>$50000</code>.</li>
</ul>

<p>The result table <strong>must</strong> contain all three categories. If there are no accounts in a category,&nbsp;return&nbsp;<code>0</code>.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Accounts table:
+------------+--------+
| account_id | income |
+------------+--------+
| 3          | 108939 |
| 2          | 12747  |
| 8          | 87709  |
| 6          | 91796  |
+------------+--------+
<strong>Output:</strong> 
+----------------+----------------+
| category       | accounts_count |
+----------------+----------------+
| Low Salary     | 1              |
| Average Salary | 0              |
| High Salary    | 3              |
+----------------+----------------+
<strong>Explanation:</strong> 
Low Salary: Account 2.
Average Salary: No accounts.
High Salary: Accounts 3, 6, and 8.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Temporary Table + Grouping + Left Join

We can first create a temporary table containing all salary categories, and then count the number of bank accounts for each salary category. Finally, we use a left join to connect the temporary table with the result table to ensure that the result table contains all salary categories.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    S AS (
        SELECT 'Low Salary' AS category
        UNION
        SELECT 'Average Salary'
        UNION
        SELECT 'High Salary'
    ),
    T AS (
        SELECT
            CASE
                WHEN income < 20000 THEN "Low Salary"
                WHEN income > 50000 THEN 'High Salary'
                ELSE 'Average Salary'
            END AS category,
            COUNT(1) AS accounts_count
        FROM Accounts
        GROUP BY 1
    )
SELECT category, IFNULL(accounts_count, 0) AS accounts_count
FROM
    S
    LEFT JOIN T USING (category);
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Filtering + Merging

We can filter out the number of bank accounts for each salary category separately, and then merge the results. Here, we use `UNION` to merge the results.

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT 'Low Salary' AS category, IFNULL(SUM(income < 20000), 0) AS accounts_count FROM Accounts
UNION
SELECT
    'Average Salary' AS category,
    IFNULL(SUM(income BETWEEN 20000 AND 50000), 0) AS accounts_count
FROM Accounts
UNION
SELECT 'High Salary' AS category, IFNULL(SUM(income > 50000), 0) AS accounts_count FROM Accounts;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
