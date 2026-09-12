---
comments: true
difficulty: Medium
tags:
    - Database
---

<!-- problem:start -->

# [2308. Arrange Table by Gender 🔒](https://leetcode.com/problems/arrange-table-by-gender)

[中文文档](/solution/2300-2399/2308.Arrange%20Table%20by%20Gender/README.md)

## Description

<!-- description:start -->

<p>Table: <code>Genders</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| user_id     | int     |
| gender      | varchar |
+-------------+---------+
user_id is the primary key (column with unique values) for this table.
gender is ENUM (category) of type &#39;female&#39;, &#39;male&#39;, or &#39;other&#39;.
Each row in this table contains the ID of a user and their gender.
The table has an equal number of &#39;female&#39;, &#39;male&#39;, and &#39;other&#39;.
</pre>

<p>&nbsp;</p>

<p>Write a solution&nbsp;to rearrange the <code>Genders</code> table such that the rows alternate between <code>&#39;female&#39;</code>, <code>&#39;other&#39;</code>, and <code>&#39;male&#39;</code> in order. The table should be rearranged such that the IDs of each gender are sorted in ascending order.</p>

<p>Return the result table in <strong>the mentioned order</strong>.</p>

<p>The&nbsp;result format is shown in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Genders table:
+---------+--------+
| user_id | gender |
+---------+--------+
| 4       | male   |
| 7       | female |
| 2       | other  |
| 5       | male   |
| 3       | female |
| 8       | male   |
| 6       | other  |
| 1       | other  |
| 9       | female |
+---------+--------+
<strong>Output:</strong> 
+---------+--------+
| user_id | gender |
+---------+--------+
| 3       | female |
| 1       | other  |
| 4       | male   |
| 7       | female |
| 2       | other  |
| 5       | male   |
| 9       | female |
| 6       | other  |
| 8       | male   |
+---------+--------+
<strong>Explanation:</strong> 
Female gender: IDs 3, 7, and 9.
Other gender: IDs 1, 2, and 6.
Male gender: IDs 4, 5, and 8.
We arrange the table alternating between &#39;female&#39;, &#39;other&#39;, and &#39;male&#39;.
Note that the IDs of each gender are sorted in ascending order.
</pre>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Rows must alternate by gender, and each gender must stay ordered by $user\_id$. Sorting by gender alone yields three contiguous blocks.
>
> Rank $user\_id$ inside each gender, and map female / other / male to $0,1,2$. Order by that rank, then by the mapping, so the three genders at the same rank appear in the required sequence.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
WITH
    t AS (
        SELECT
            *,
            RANK() OVER (
                PARTITION BY gender
                ORDER BY user_id
            ) AS rk1,
            CASE
                WHEN gender = 'female' THEN 0
                WHEN gender = 'other' THEN 1
                ELSE 2
            END AS rk2
        FROM Genders
    )
SELECT user_id, gender
FROM t
ORDER BY rk1, rk2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> Method 1 materializes two sort keys in a CTE. The same window rank can sit in $ORDER\ BY$: partition by gender, rank by $user\_id$, then use gender’s lexicographic order (female, male, other) as the second key, dropping the explicit $CASE$ and the extra $WITH$.

<!-- thinking:end -->

<!-- tabs:start -->

#### MySQL

```sql
SELECT
    user_id,
    gender
FROM Genders
ORDER BY
    (
        RANK() OVER (
            PARTITION BY gender
            ORDER BY user_id
        )
    ),
    2;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
